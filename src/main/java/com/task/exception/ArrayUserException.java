package com.task.exception;

/**
 * Custom exception for array-related exceptional situations.
 */
public class ArrayUserException extends Exception {

    public ArrayUserException(){
        super();
    }

    public ArrayUserException(String message) {

        super(message);
    }

    public ArrayUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayUserException(Throwable cause){
        super(cause);
    }
}
