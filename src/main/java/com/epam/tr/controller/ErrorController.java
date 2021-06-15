package com.epam.tr.controller;

import com.epam.tr.error.ErrorResponse;
import com.epam.tr.exceptions.WrongFileException;
import com.epam.tr.exceptions.WrongUserCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ErrorController {
    private static final String ERROR = "error code =";
    @ExceptionHandler({WrongFileException.class, WrongUserCredentials.class})
    public final ResponseEntity<Object> handleBindExceptions(Exception ex) {
        String details = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(ERROR + BAD_REQUEST, details);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptions(Exception ex) {
        String details = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(ERROR + INTERNAL_SERVER_ERROR, details);
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }
}
