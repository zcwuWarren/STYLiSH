package org.example.stylish.exception;

public class NonExistedAccount extends RuntimeException {
    public NonExistedAccount(String message) {
        super(message);
    }
}
