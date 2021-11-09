package com.epam.architecture.exceptions;

public class RestoreFromDataSourceException extends RuntimeException {
    public RestoreFromDataSourceException() {
        super();
    }

    public RestoreFromDataSourceException(String message) {
        super(message);
    }

    public RestoreFromDataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestoreFromDataSourceException(Throwable cause) {
        super(cause);
    }
}
