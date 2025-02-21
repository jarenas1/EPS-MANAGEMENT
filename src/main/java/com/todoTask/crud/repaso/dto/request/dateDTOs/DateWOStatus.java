package com.todoTask.crud.repaso.dto.request.dateDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateWOStatus {
    private Long id;

    private LocalDateTime dateTime;

    private String reason;

    private String notes;

    private Long patient;

    private Long doctor;

    private Long shift;
}
