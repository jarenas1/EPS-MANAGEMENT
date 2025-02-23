package com.todoTask.crud.repaso.exceptions;

public class DateOverTheHourException extends RuntimeException{
    public DateOverTheHourException(String message) {
        super(message);
    }
}
