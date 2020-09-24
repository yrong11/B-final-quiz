package com.example.demo.exception;

public class TraineeNotExistException extends RuntimeException{
//    private String message;

    public TraineeNotExistException(String message) {
        super(message);
    }
}
