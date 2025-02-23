package com.todoTask.crud.repaso.exceptions;

public class UserExsistentException extends  RuntimeException{
    public UserExsistentException(String message) {
        super(message);
    }
}
