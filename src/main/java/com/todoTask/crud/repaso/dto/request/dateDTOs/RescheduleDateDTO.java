package com.todoTask.crud.repaso.dto.request.dateDTOs;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Getter
@Builder
public class RescheduleDateDTO {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime newDate;

    private Long shiftId;

}
