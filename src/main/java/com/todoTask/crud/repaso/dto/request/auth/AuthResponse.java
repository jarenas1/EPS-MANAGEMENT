package com.todoTask.crud.repaso.dto.request.auth;

public record AuthResponse(String email, String message, String token, boolean status) {
}
