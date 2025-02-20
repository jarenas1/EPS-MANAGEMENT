package com.todoTask.crud.repaso.dto.request.doctorDTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorCreateDTO {

    private String email;

    private String password;

    private String name;

    private String lastname;

    private String specialty;

}
