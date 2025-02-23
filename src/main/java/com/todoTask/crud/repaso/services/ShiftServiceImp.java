package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.dto.request.shiftDTOs.ShiftCreateDto;
import com.todoTask.crud.repaso.dto.request.shiftDTOs.ShiftUpdateDto;
import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.ShiftEntity;
import com.todoTask.crud.repaso.exceptions.CantCreateShiftException;
import com.todoTask.crud.repaso.exceptions.DoctorNotFoundException;
import com.todoTask.crud.repaso.exceptions.ShiftNotFoundException;
import com.todoTask.crud.repaso.repositories.DoctorRepository;
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

    @Autowired
    DoctorRepository doctorRepository;

    @Transactional
    @Override
    public List<ShiftEntity> findByDoctorAndDayAndActive(Long idDoctor, Day day, Boolean active) {
        DoctorEntity doctorEntity = doctorRepository.findById(idDoctor).orElseThrow(()-> new DoctorNotFoundException("we cant found the doctor"));
        return shiftRepository.findByDoctorAndDayAndActive(doctorEntity,day,active);
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
    public ResponseEntity<ShiftEntity> save(ShiftCreateDto shiftEntity) {
        DoctorEntity doctorEntity = doctorRepository.findById(shiftEntity.getDoctorId()).orElseThrow(()-> new DoctorNotFoundException("we cant found the doctor"));
        //Verify if doctor already have a turn in this day to throw an exception saing that the turn can´t be created.
        List<ShiftEntity> shiftByDay = shiftRepository.findByDoctorAndDayAndActive(doctorEntity,shiftEntity.getDay(),true);

        if (!shiftByDay.isEmpty()) {
            throw new CantCreateShiftException("this day already has a shift associated with the doctor");
        }
        ShiftEntity shiftToSave = ShiftEntity.builder()
                .day(shiftEntity.getDay())
                .startTime(shiftEntity.getStartTime())
                .endTime(shiftEntity.getEndTime())
                .doctor(doctorEntity)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(shiftRepository.save(shiftToSave));
    }

    @Override
    public ResponseEntity<ShiftEntity> update(ShiftUpdateDto shiftEntity) {
        ShiftEntity shiftToUpdate = ShiftEntity.builder()
                .id(shiftEntity.getId())
                .day(shiftEntity.getDay())
                .startTime(shiftEntity.getStartTime())
                .endTime(shiftEntity.getEndTime())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(shiftRepository.save(shiftToUpdate));
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
