package com.todoTask.crud.repaso.services.interfaces;

import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.ShiftEntity;
import com.todoTask.crud.repaso.tools.enums.Day;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IShiftService {

    List<ShiftEntity> findByDoctorAndDayAndActive(Long idDoctor, Day day, Boolean active);
    List<ShiftEntity> findByDoctorAndActive(DoctorEntity doctor, Boolean active);
    Page<ShiftEntity> findAll(Pageable pageable);
    ShiftEntity findById(Long id);
    ResponseEntity<ShiftEntity> save(ShiftEntity shiftEntity);
    ResponseEntity <ShiftEntity> update(ShiftEntity shiftEntity);
    ShiftEntity delete(Long id);


}
