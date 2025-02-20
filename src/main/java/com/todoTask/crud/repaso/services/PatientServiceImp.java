package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.dto.request.patientDTOs.PatientUpdateDTO;
import com.todoTask.crud.repaso.entities.PatientEntity;
import com.todoTask.crud.repaso.entities.UserEntity;
import com.todoTask.crud.repaso.error_handler.PatientNotFoundException;
import com.todoTask.crud.repaso.repositories.PatientRepository;
import com.todoTask.crud.repaso.repositories.UserRepository;
import com.todoTask.crud.repaso.services.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PatientServiceImp implements IPatientService {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;

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
    public ResponseEntity<PatientEntity> update(PatientUpdateDTO patientEntity) {
        PatientEntity patientToUpdate = patientRepository.findById(patientEntity.getId()).orElseThrow(()-> new PatientNotFoundException("we cant found the patient"));
        UserEntity userToUpdate = userRepository.findByPatient(patientToUpdate).orElseThrow(()-> new PatientNotFoundException("we cant found the user associated with the patient"));

        userToUpdate.setName(patientEntity.getName());
        userToUpdate.setEmail(patientEntity.getEmail());
        userToUpdate.setLastname(patientEntity.getLastname());

        userRepository.save(userToUpdate);
        return ResponseEntity.ok().body(patientToUpdate);
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
