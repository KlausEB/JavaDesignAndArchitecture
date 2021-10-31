package com.epam.designAndArchitecture.exceptions;

public class HistoryException extends RuntimeException {
    public HistoryException() {
        super();
    }

    public HistoryException(String message) {
        super(message);
    }

    public HistoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public HistoryException(Throwable cause) {
        super(cause);
    }
}
