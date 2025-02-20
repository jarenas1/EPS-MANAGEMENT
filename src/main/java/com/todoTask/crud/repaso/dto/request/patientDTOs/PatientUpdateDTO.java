package com.todoTask.crud.repaso.dto.request.patientDTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientUpdateDTO {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String lastname;
}
