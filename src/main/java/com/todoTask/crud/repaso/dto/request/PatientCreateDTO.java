package com.todoTask.crud.repaso.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientCreateDTO {

    private String email;

    private String password;

    private String name;

    private String lastname;

}
