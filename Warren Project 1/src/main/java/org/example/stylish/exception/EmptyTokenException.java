package org.example.stylish.exception;

public class EmptyTokenException extends RuntimeException {
    public EmptyTokenException(String message) {
        super(message);
    }
}
