package com.lms.demo.error;

public class DuplicateEntityException extends Exception{
    public DuplicateEntityException(String message) {
        super(message);
    }
}
