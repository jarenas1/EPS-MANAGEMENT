package com.todoTask.crud.repaso.services.interfaces;

import com.todoTask.crud.repaso.dto.request.DateWOStatus;
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
    List<DateEntity> findByDoctorAndDateTimeBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
    List<DateEntity> findByPatientAndStatus(Long PatientId, DateStatus status);
    List<DateEntity> findByDoctorAndStatus(Long doctorId, DateStatus status);
    ResponseEntity<DateEntity> save(DateWOStatus dateWOStatus);
    ResponseEntity<DateEntity>putDateAsDone(Long dateId);
    ResponseEntity<DateEntity> cancelDate(Long dateId);
    ResponseEntity<DateEntity> update(DateWOStatus dateEntity);
    ResponseEntity<DateEntity> updateNotes(String notes, Long dateId);
    Page<DateEntity> findAll(Pageable pageable);
    Boolean delete(Long id);
    ResponseEntity<DateEntity> rescheduleDate(Long id, LocalDateTime newDate);

}
