package com.lms.demo.error;

public class NotAnAdminException extends Exception{

    public NotAnAdminException(String message) {
        super(message);
    }
}
