package com.todoTask.crud.repaso.error_handler;

public class ConflictDatesException extends RuntimeException{
    public ConflictDatesException(String message) {
        super(message);
    }
}
