package com.todoTask.crud.repaso.services.interfaces;

import com.todoTask.crud.repaso.dto.request.patientDTOs.PatientUpdateDTO;
import com.todoTask.crud.repaso.entities.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IPatientService {
    Optional<PatientEntity> findByDocument(String document);
//    ResponseEntity<PatientEntity> save(PatientEntity patientEntity);
    ResponseEntity<PatientEntity> update(PatientUpdateDTO patientEntity);
    Page<PatientEntity> findAll(Pageable pageable);
    Boolean delete(Long id);
}
