package com.lms.demo.error;

public class ErrorResponseMessages {
    public static String notAnAdminForRemoveUser = "Only Admins can remove users";
    public static String notAnAdminForAddBook = "Only Admins can add books";

    public static String nullNameValueForUser = "Name must be provided";
    public static String nullContactValueForUser = "Contact Number must be provided";
    public static String illegalNameValueForUser = "Name is not valid";
    public static String illegalContactValueForUser = "Contact Number is not valid";
    public static String duplicateNameForUser = "User with same name already exists";
    public static String duplicateContactForUser = "User with same contact number already exists";

    public static String nullTitleValueForBook = "Book must have a title";
    public static String nullAuthorValueForBook = "Book must have an author";
    public static String nullAdminValueForBook = "Only Admins can add new book";
    public static String nullCopiesValueForBook = "Number of Copies must be between 1 to 5";
    public static String duplicateBooK = "Book already exits";

    public static String nullIdValueForBorrow = "Library id is required";
    public static String nullIsbnValueForBorrow = "ISBN code is required";

    public static String userNotFound = "User doesn't exist";
    public static String bookNotFound = "Book doesn't exist";
    public static String copiesNotAvailable = "All copies of the book are borrowed";

    public static String illegalIdValueForLibraryCard = "Library id is required";

    public static String nullIssueIdForReturn = "Issue Id is required";
    public static String nullLibraryIdForReturn = "Library Id is required";
    public static String nullBarcodeForReturn = "Barcode is required";
    public static String borrowDetailsNotFound = "No such borrow has been made";
    public static String invalidLibraryIdForReturn = "Library id is wrong";
    public static String invalidBarcodeForReturn = "Barcode is wrong";
}
