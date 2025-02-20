package com.todoTask.crud.repaso.dto.response;

public record AuthResponse(String email, String message, String token, boolean status) {
}
