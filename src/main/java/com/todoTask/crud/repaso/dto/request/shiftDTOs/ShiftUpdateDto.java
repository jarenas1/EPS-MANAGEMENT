package com.todoTask.crud.repaso.dto.request.shiftDTOs;

import com.todoTask.crud.repaso.tools.enums.Day;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ShiftUpdateDto {

    private Long id;

    private Day day;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Boolean active = true;
}
