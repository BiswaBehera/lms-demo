package com.lms.demo.error;

public class InvalidEntityException extends Exception{
    public InvalidEntityException(String message) {
        super(message);
    }
}
