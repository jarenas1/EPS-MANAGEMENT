package com.todoTask.crud.repaso.services.interfaces;

import com.todoTask.crud.repaso.dto.request.doctorDTOs.DoctorUpdateDTO;
import com.todoTask.crud.repaso.entities.DoctorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IDoctorService {
    ResponseEntity<?> findBySpeciality(String specialty);
    ResponseEntity<DoctorEntity> update(DoctorUpdateDTO doctorEntity);
    Page<DoctorEntity> findAll(Pageable pageable);
    Boolean delete(Long id);
}
