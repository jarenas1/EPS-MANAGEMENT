package com.todoTask.crud.repaso.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctoUpdateDTO {

    private Long id;

    private String email;

    private String name;

    private String lastname;

    private String specialty;
}
