package com.todoTask.crud.repaso.repositories;

import com.todoTask.crud.repaso.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    Optional<PatientEntity> findByDocument(String document);
}
