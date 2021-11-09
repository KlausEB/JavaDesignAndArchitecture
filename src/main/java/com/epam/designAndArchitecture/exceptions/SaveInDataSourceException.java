package com.epam.designAndArchitecture.exceptions;

public class SaveInDataSourceException extends RuntimeException {
    public SaveInDataSourceException() {
        super();
    }

    public SaveInDataSourceException(String message) {
        super(message);
    }

    public SaveInDataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveInDataSourceException(Throwable cause) {
        super(cause);
    }
}
