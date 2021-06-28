package com.hackerrank.orm.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {

        super("No data found");
    }
}
