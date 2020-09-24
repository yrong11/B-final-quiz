package com.example.demo.exception;

public class NotExistException extends RuntimeException{
//    private String message;

    public NotExistException(String message) {
        super(message);
    }
}
