package com.todoTask.crud.repaso.repositories;

import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository  extends JpaRepository<DoctorEntity, Long> {
    List<DoctorEntity> findByEspecialidad(SpecialtyEntity specialty);
}
