package com.todoTask.crud.repaso.error_handler;

public class UserExsistentException extends  RuntimeException{
    public UserExsistentException(String message) {
        super(message);
    }
}
