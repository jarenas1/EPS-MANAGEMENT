package com.todoTask.crud.repaso.exceptions.layouts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class Exception {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
