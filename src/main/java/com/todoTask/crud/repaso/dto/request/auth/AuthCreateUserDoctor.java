package com.todoTask.crud.repaso.dto.request.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthCreateUserDoctor(
        @NotBlank String email,
        @NotBlank String password,
        @NotNull String name,
        @NotNull String lastname,
        @NotNull String specialty
) {
}
