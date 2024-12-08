package com.todoTask.crud.repaso.error_handler;

public class DateOverTheHourException extends RuntimeException{
    public DateOverTheHourException(String message) {
        super(message);
    }
}
