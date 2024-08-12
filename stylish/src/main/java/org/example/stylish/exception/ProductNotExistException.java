package org.example.stylish.exception;

public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException(String message) {
        super(message);
    }
}
