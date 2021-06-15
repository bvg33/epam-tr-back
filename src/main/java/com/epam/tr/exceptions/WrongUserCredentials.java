package com.epam.tr.exceptions;

public class WrongUserCredentials extends Exception {
    public WrongUserCredentials() {
    }

    public WrongUserCredentials(String message) {
        super(message);
    }

    public WrongUserCredentials(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongUserCredentials(Throwable cause) {
        super(cause);
    }
}
