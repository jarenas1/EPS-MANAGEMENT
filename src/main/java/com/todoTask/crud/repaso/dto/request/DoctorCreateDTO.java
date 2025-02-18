package com.todoTask.crud.repaso.dto.request;

import com.todoTask.crud.repaso.entities.DateEntity;
import com.todoTask.crud.repaso.entities.ShiftEntity;
import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import com.todoTask.crud.repaso.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DoctorCreateDTO {

    private String email;

    private String password;

    private String name;

    private String lastname;

    private String specialty;

}
