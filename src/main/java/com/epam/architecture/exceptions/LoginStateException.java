package com.epam.architecture.exceptions;

public class LoginStateException extends RuntimeException {
    public LoginStateException() {
        super();
    }

    public LoginStateException(String message) {
        super(message);
    }

    public LoginStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginStateException(Throwable cause) {
        super(cause);
    }
}
