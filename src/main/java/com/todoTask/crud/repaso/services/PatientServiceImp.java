package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.entities.PatientEntity;
import com.todoTask.crud.repaso.error_handler.PatientNotFoundException;
import com.todoTask.crud.repaso.repositories.PatientRepository;
import com.todoTask.crud.repaso.services.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class PatientServiceImp implements IPatientService {

    @Autowired
    PatientRepository patientRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<PatientEntity> findByDocument(String document) {
        return patientRepository.findByDocument(document);
    }

//    @Transactional
//    @Override
//    public ResponseEntity<PatientEntity> save(PatientEntity patientEntity) {
//        PatientEntity patientSaved = patientRepository.save(patientEntity);
//        return ResponseEntity.status(HttpStatus.CREATED).body(patientSaved);
//    }

    @Transactional
    @Override
    public ResponseEntity<PatientEntity> update(PatientEntity patientEntity) {
        PatientEntity patientUpdated = patientRepository.save(patientEntity);
        return ResponseEntity.ok().body(patientUpdated);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PatientEntity> findAll(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<PatientEntity> patientEntity = patientRepository.findById(id);
        if (patientEntity.isPresent()){
            patientRepository.delete(patientEntity.get());
            return true;
        }else{
            throw  new PatientNotFoundException(String.format("We can't find a patient with the id %s", id));
        }
    }
}
