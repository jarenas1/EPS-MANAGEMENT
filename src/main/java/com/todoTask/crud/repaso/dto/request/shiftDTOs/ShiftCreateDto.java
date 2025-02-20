package com.todoTask.crud.repaso.dto.request.shiftDTOs;

import com.todoTask.crud.repaso.tools.enums.Day;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShiftCreateDto {

    private Day day;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long doctorId;
}
