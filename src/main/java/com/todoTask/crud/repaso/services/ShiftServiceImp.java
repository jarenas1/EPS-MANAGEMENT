package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.ShiftEntity;
import com.todoTask.crud.repaso.error_handler.ShiftNotFoundException;
import com.todoTask.crud.repaso.repositories.ShiftRepository;
import com.todoTask.crud.repaso.services.interfaces.IShiftService;
import com.todoTask.crud.repaso.tools.enums.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
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
    public ShiftEntity findById(Long id) {
        Optional<ShiftEntity> optionalShift = shiftRepository.findById(id);
        return optionalShift.orElseThrow(() -> new ShiftNotFoundException(String.format("We can't find a shift with the id %s", id)));
    }

    @Override
    public ResponseEntity<ShiftEntity> save(ShiftEntity shiftEntity) {
        ShiftEntity shiftEntitySaved = shiftRepository.save(shiftEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(shiftEntity);
    }

    @Override
    public ResponseEntity<ShiftEntity> update(ShiftEntity shiftEntity) {
        ShiftEntity shiftEntityUpdated = shiftRepository.save(shiftEntity);
        return ResponseEntity.status(HttpStatus.OK).body(shiftEntityUpdated);
    }

    @Override
    public ShiftEntity delete(Long id) {
        return shiftRepository.findById(id)
            .map(shift -> {
                shiftRepository.delete(shift);
                return shift;
            })
            .orElseThrow(() -> new ShiftNotFoundException(String.format("We can't find a shift with the id %s", id)));
    }
}
