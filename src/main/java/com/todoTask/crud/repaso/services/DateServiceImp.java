package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.entities.DateEntity;
import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.PatientEntity;
import com.todoTask.crud.repaso.error_handler.DateNotFoundException;
import com.todoTask.crud.repaso.error_handler.DoctorNotFoundException;
import com.todoTask.crud.repaso.error_handler.PatientNotFoundException;
import com.todoTask.crud.repaso.repositories.DateRepository;
import com.todoTask.crud.repaso.repositories.DoctorRepository;
import com.todoTask.crud.repaso.repositories.PatientRepository;
import com.todoTask.crud.repaso.services.interfaces.IDateService;
import com.todoTask.crud.repaso.tools.enums.DateStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DateServiceImp implements IDateService {

    @Autowired
    DateRepository dateRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public List<DateEntity> findByDoctorAndDateTimeBetween(Long doctorId, LocalDateTime start, LocalDateTime end) {
        Optional<DoctorEntity> optionalDoctorEntity = doctorRepository.findById(doctorId);
        if (optionalDoctorEntity.isPresent()){
            List<DateEntity> dates = dateRepository.findByDoctorAndDateTimeBetween(optionalDoctorEntity.get(), start,end);
            return dates;
        }else{
            throw new DoctorNotFoundException( String.format("We cant found the doctor with the id %a", doctorId));
        }
    }

    @Override
    public List<DateEntity> findByPatientAndStatus(Long PatientId, DateStatus status) {
        Optional<PatientEntity> optionalPatientEntity = patientRepository.findById(PatientId);
        if (optionalPatientEntity.isPresent()){
            List<DateEntity> dates = dateRepository.findByPatientAndStatus(optionalPatientEntity.get(), status);
            return dates;
        }else{
            throw new PatientNotFoundException(String.format("We cant found the patient with the id %a", PatientId));
        }
    }

    @Override
    public List<DateEntity> findByDoctorAndStatus(Long doctorId, DateStatus status) {
        Optional<DoctorEntity> optionalDoctorEntity = doctorRepository.findById(doctorId);
        if (optionalDoctorEntity.isPresent()){
            List<DateEntity> dates = dateRepository.findByDoctorAndStatus(optionalDoctorEntity.get(), status);
            return dates;
        }else{
            throw new DoctorNotFoundException( String.format("We cant found the doctor with the id %a", doctorId));
        }
    }

    @Override
    public ResponseEntity<DateEntity> save(DateEntity dateEntity) {
        DateEntity dateEntityCreated = dateRepository.save(dateEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(dateEntityCreated);
    }

    @Override
    public ResponseEntity<DateEntity> update(DateEntity dateEntity) {
        DateEntity dateUpdated = dateRepository.save(dateEntity);
        return ResponseEntity.ok(dateUpdated);
    }

    @Override
    public Page<DateEntity> findAll(Pageable pageable) {
        return dateRepository.findAll(pageable);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<DateEntity> dateOptional = dateRepository.findById(id);
        if (dateOptional.isPresent()){
            dateRepository.delete(dateOptional.get());
            return true;
        }else{
            throw  new DateNotFoundException(String.format("We can't find a date with the id %s", id));
        }
    }
}
