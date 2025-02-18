package com.todoTask.crud.repaso.dto.request.auth;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthCreateRoleRequest(@Size(max = 1, message = "the user cannot have more than 1 roles") List<String> roleListName) {
}
