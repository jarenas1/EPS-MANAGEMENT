package com.todoTask.crud.repaso.repositories;

import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository  extends JpaRepository<DoctorEntity, Long> {
    List<DoctorEntity> findByEspecialidad(SpecialtyEntity specialty);
}
