package com.todoTask.crud.repaso.dto.request.doctorDTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorUpdateDTO {

    private Long id;

    private String email;

    private String name;

    private String lastname;

    private String specialty;
}
