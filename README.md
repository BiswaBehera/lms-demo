Library Management System
---------------------------------------------
## **Database Design**

#### User:
    id: long/pk,
    password: string/required,
    name: string/required,
    contactNo: long/required,
    isAdmin: bool

#### Book:
    isbnCode: long/pk,
    title: string/required,
    language: string,
    subject: string,
    authorId: long/required/fk

#### Author:
    id: long/pk,
    name: string/required

#### BookItem: 
    barcode: long/pk,
    available: bool,
    isbnCode: long/fk

#### BorrowDetails:
    id: long/pk,
    checkoutDate: date/required,
    dueDate: date,
    returnDate: date,
    barcode: long/fk,
    libraryId: long/fk
 
---------------------------------------------
## **_Apis_**

#### Add User

**POST** **/user/add**\
creates a new user

URL params: None\
Header:\
Content-type - application/json\

Body:

    {
        "name": string/required,
        "contact_number": string/required,
        "password": string,
        "is_admin": boolean
    }

Success response\
Code: 200\
Content:

    {
        "library_id": long
        "name": string
        "contact_number": string
        "message": string
    }

error response:

    {
        "status": "NOT_ACCEPTABLE",
        "message": "Name must be provided"
    } or

	if name is less than 3 characters
	{
    	"status": "NOT_ACCEPTABLE",
    	"message": "Name is not valid"
	} or
 
	if user with same name already exists
	{
    	"status": "CONFLICT",
    	"message": "User with same name already exists"
	} or
 
	{
    	"status": "NOT_ACCEPTABLE",
    	"message": "Contact Number must be provided"
	} or
	
	if contact number is not exactly 10 characters long
	{
    	"status": "NOT_ACCEPTABLE",
    	"message": "Contact Number is not valid"
	} or
 
	if user with same contact number already exists
	{
    	"status": "CONFLICT",
    	"message": "User with same contact number already exists"
	}
-----------------------------------------------------------

**POST /book/add**

add a new book to library

URL params: None\
Header:\
Content-type : application/json

Request Body:

    {
        "admin_id": long/required,
        "title": string/required,
        "language": string,
        "genre": string,
        "author_name": string/required,
        "number_of_copies": int
    }

Success response\
Code: 200\
Response Body:

    {
        "title": string,
        "genre": string,
        "author_name": string/required,
        "message": string
    }

error response

	{
    	"status": "UNAUTHORIZED",
    	"message": "Only Admins can add books"
	} or
 
	{
    	"status": "NOT_ACCEPTABLE",
    	"message": "Book must have a title"
	} or 
 
	{
    	"status": "NOT_ACCEPTABLE",
    	"message": "Book must have an author"
	} or 
 
	{
    	"status": "CONFLICT",
    	"message": "Book already exists"
	}
 
-----------------------------------------------------------

**POST /user/get_library_card**

fetches the library card of a user

URL params: None\
Header:\
Content-type: application/json

Request Body:

    {
        "library_id": long/required
    }

Success response\
Code: 200

Response Body: 

    {
        "library_id": long,
        "name": string,
        "contact_no": string
        "list_of_active_borrows": [
            {
                "issue_id": long,
                "isbn_code": long,
                "book_title": string,
                "barcode": long,
                "due_date": datetime,
                "checkout_date": datetime,
                "fine": int,
                "paid": boolean
            },
            ...
        ]
    }

error response:

    {
        "status": "NOT_ACCEPTABLE",
        "message": "Library id is required"
    } or

	{
    	"status": "NOT_FOUND",
    	"message": "User doesn't exist"
	}
 
-----------------------------------------------------------

**POST /user/borrow**

rent a book from library

URL Params: None\
Headers:\
Content-Type: application/json

Request Body:

    {
        "library_id": long/required,
        "isbn_code": long/required
    }

Success response\
Code: 200

Response Body:

    {
        "barcode": long,
        "due_date": datetime,
        "message": string
    }

error response:

	{
    	"status": "NOT_ACCEPTABLE",
    	"message": "Library id is required"
	} or
 
	{
    	"status": "NOT_FOUND",
    	"message": "User doesn't exist"
	} or 
 
	{
    	"status": "NOT_ACCEPTABLE",
    	"message": "ISBN code is required"
	} or 
 
	{
    	"status": "NOT_FOUND",
    	"message": "Book doesn't exist"
	} or 
 
	{
    	"status": "CONFLICT",
    	"message": "All copies of the book are borrowed"
	}
 
-----------------------------------------------------------

**PUT /user/return**

return a book to library

URL Params: None\
Headers:\
Content-Type: application/json

Request Body:

    {
        "issue_id": long/required,
        "library_id": long/required,
        "barcode": long/required
    }

Success response\
Code: 200\

Response Body:

    {
        "issue_id": long,
        "fine": int,
        "message": string
    }

error response:

	{
    	"status": "NOT_ACCEPTABLE",
    	"message": "Issue Id is required"
	} or 
 
	{
    	"status": "NOT_FOUND",
    	"message": "No such borrow has been made"
	} or 
 
	{
    	"status": "NOT_ACCEPTABLE",
    	"message": "Library Id is required"
	} or
 
	{
    	"status": "NOT_FOUND",
    	"message": "User doesn't exist"
	} or
 
	if library id doesn't match the library id in borrow details
	{
    	"status": "CONFLICT",
    	"message": "Library id is wrong"
	} or 
 
	{
    	"status": "NOT_ACCEPTABLE",
    	"message": "Barcode is required"
	} or 
 
	{
    	"status": "CONFLICT",
    	"message": "Barcode is wrong"
	}
 
-----------------------------------------------------------

**GET /book/:search_type/:search_string**

search books in library

URL Params:\

    search_type: enum SearchType
    search_string: string

Headers:\
Content-Type: application/json

Request Body: None

Success response\
Code: 200

Response Body:

    [
    <Book>, ...
    ]

error response: None
 

