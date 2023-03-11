package ru.netology.product.exception;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String s) {
        super (s);
    }
}
