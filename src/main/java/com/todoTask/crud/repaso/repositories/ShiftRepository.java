package com.todoTask.crud.repaso.repositories;

import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.ShiftEntity;
import com.todoTask.crud.repaso.tools.enums.Day;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftRepository extends JpaRepository<ShiftEntity, Long> {
    List<ShiftEntity> findByDoctorAndDayAndActive(DoctorEntity doctorEntity, Day day, Boolean active);
    List<ShiftEntity> findByDoctorAndActive(DoctorEntity doctor, Boolean active);
    Page<ShiftEntity> findAll(Pageable pageable);
}
