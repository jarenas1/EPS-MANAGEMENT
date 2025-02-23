package com.todoTask.crud.repaso.exceptions.layouts;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Exception {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
