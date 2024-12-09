package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.PatientEntity;
import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import com.todoTask.crud.repaso.error_handler.DoctorNotFoundException;
import com.todoTask.crud.repaso.error_handler.PatientNotFoundException;
import com.todoTask.crud.repaso.error_handler.SpecialityNotFoundException;
import com.todoTask.crud.repaso.repositories.DoctorRepository;
import com.todoTask.crud.repaso.repositories.SpecialityRepository;
import com.todoTask.crud.repaso.services.interfaces.IDoctorService;
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
public class DoctorServiceImp implements IDoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    SpecialityRepository specialityRepository;

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<?> findBySpeciality(Long idSpecialty) {

        List<DoctorEntity> doctorEntities = doctorRepository.findBySpeciality(
                specialityRepository.findById(idSpecialty).orElseThrow(
                        ()-> new SpecialityNotFoundException("we cant found the speciality")));

        return ResponseEntity.ok(doctorEntities);
    }

    @Transactional
    @Override
    public ResponseEntity<DoctorEntity> save(DoctorEntity doctorEntity) {
        DoctorEntity doctorSaved = doctorRepository.save(doctorEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorSaved);
    }

    @Transactional
    @Override
    public ResponseEntity<DoctorEntity> update(DoctorEntity doctorEntity) {
        DoctorEntity doctorSaved = doctorRepository.save(doctorEntity);
        return ResponseEntity.ok(doctorSaved);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<DoctorEntity> findAll(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<DoctorEntity> doctorEntity = doctorRepository.findById(id);
        if (doctorEntity.isPresent()){
            doctorRepository.delete(doctorEntity.get());
            return true;
        }else{
            throw  new DoctorNotFoundException(String.format("We can't find a doctor with the id %s", id));
        }
    }
}
