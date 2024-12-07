package com.todoTask.crud.repaso.repositories;

import com.todoTask.crud.repaso.entities.DateEntity;
import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.PatientEntity;
import com.todoTask.crud.repaso.tools.enums.DateStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DateRepository extends JpaRepository<DateEntity, Long> {
    List<DateEntity> findByDoctorAndDateTimeBetween(DoctorEntity doctor, LocalDateTime start, LocalDateTime end);
    List<DateEntity> findByPatientAndStatus(PatientEntity patient, DateStatus status);
    List<DateEntity> findByDoctorAndStatus(DoctorEntity doctor, DateStatus status);
}
