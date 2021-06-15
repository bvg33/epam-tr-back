package com.epam.tr.exceptions;

public class NotValidTokenException extends Exception{
    public NotValidTokenException() {
    }

    public NotValidTokenException(String message) {
        super(message);
    }

    public NotValidTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidTokenException(Throwable cause) {
        super(cause);
    }
}
