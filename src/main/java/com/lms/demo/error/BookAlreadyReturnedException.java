package com.lms.demo.error;

public class BookAlreadyReturnedException extends Exception{
    public BookAlreadyReturnedException(String message) {
        super(message);
    }
}
