package com.varun.exployee.exceptions;

public class FileTooBigException extends Exception {

    private static final long serialVersionUID = 1L;

    public FileTooBigException(String message) {
        super(message);
    }

}
