package com.todoTask.crud.repaso.error_handler;

public class ShiftNotFoundException extends RuntimeException{

    public ShiftNotFoundException(String message) {
        super(message);
    }
}
