package com.todoTask.crud.repaso.services.interfaces;

import com.todoTask.crud.repaso.entities.DateEntity;
import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.PatientEntity;
import com.todoTask.crud.repaso.tools.enums.DateStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IDateService {
    List<DateEntity> findByDoctorAndDateTimeBetween(DoctorEntity doctor, LocalDateTime start, LocalDateTime end);
    List<DateEntity> findByPatientAndStatus(PatientEntity patient, DateStatus status);
    List<DateEntity> findByDoctorAndStatus(DoctorEntity doctor, DateStatus status);
    ResponseEntity<DateEntity> save(DateEntity dateEntity);
    ResponseEntity<DateEntity> update(DateEntity dateEntity);
    Page<DateEntity> findAll(Pageable pageable);
    Boolean delete(Long id);

}
