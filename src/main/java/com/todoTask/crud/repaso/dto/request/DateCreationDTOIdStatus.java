package com.todoTask.crud.repaso.dto.request;

import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.PatientEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateCreationDTOIdStatus {
    private LocalDateTime dateTime;

    private String reason;

    private String notes;

    private PatientEntity patient;

    private DoctorEntity doctor;
}
