package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handleArgNotValidException(MethodArgumentNotValidException exception) {
        ErrorResult errorResult = ErrorResult.builder().code(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .message(exception.getBindingResult().getFieldError().getDefaultMessage()).build();
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(TraineeNotExistException.class)
    public ResponseEntity<ErrorResult> handleArgNotValidException(TraineeNotExistException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(assemblyErrorResult(exception, HttpStatus.NOT_FOUND.value()));
    }

    private ErrorResult assemblyErrorResult(Exception exception, int code) {
        return ErrorResult.builder().code(code)
                .timestamp(new Date()).message(exception.getMessage()).build();
    }


}
