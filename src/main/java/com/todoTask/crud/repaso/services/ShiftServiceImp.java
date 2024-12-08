package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.ShiftEntity;
import com.todoTask.crud.repaso.repositories.ShiftRepository;
import com.todoTask.crud.repaso.services.interfaces.IShiftService;
import com.todoTask.crud.repaso.tools.enums.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class ShiftServiceImp implements IShiftService {

    @Autowired
    ShiftRepository shiftRepository;

    @Transactional
    @Override
    public List<ShiftEntity> findByDoctorAndDayAndActive(DoctorEntity doctor, Day day, Boolean active) {
        return shiftRepository.findByDoctorAndDayAndActive(doctor,day,active);
    }

    @Override
    public List<ShiftEntity> findByDoctorAndActive(DoctorEntity doctor, Boolean active) {
        return shiftRepository.findByDoctorAndActive(doctor,active);
    }

    @Override
    public Page<ShiftEntity> findAll(Pageable pageable) {
        return shiftRepository.findAll(pageable);
    }

    @Override
    public Optional<ShiftEntity> findById(Long id) {
        Optional<ShiftEntity>optionalShift = shiftRepository.findById(id);
        ShiftEntity shiftEntity = optionalShift.orElseThrow();
        return Optional.empty();
    }

    @Override
    public ResponseEntity<ShiftEntity> save(ShiftEntity shiftEntity) {
        return null;
    }

    @Override
    public ResponseEntity<ShiftEntity> update(ShiftEntity shiftEntity) {
        return null;
    }

    @Override
    public Optional<ShiftEntity> delete(Long id) {
        return Optional.empty();
    }
}
