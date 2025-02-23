package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.dto.request.doctorDTOs.DoctorUpdateDTO;
import com.todoTask.crud.repaso.entities.*;
import com.todoTask.crud.repaso.exceptions.DoctorNotFoundException;
import com.todoTask.crud.repaso.exceptions.SpecialityNotFoundException;
import com.todoTask.crud.repaso.repositories.DoctorRepository;
import com.todoTask.crud.repaso.repositories.SpecialityRepository;
import com.todoTask.crud.repaso.repositories.UserRepository;
import com.todoTask.crud.repaso.services.interfaces.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    UserRepository userRepository;

    @Autowired
    SpecialityRepository specialityRepository;

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<?> findBySpeciality(String specialty) {
        List<DoctorEntity> doctorEntities = doctorRepository.findBySpecialty(
                specialityRepository.findByName(specialty).orElseThrow(
                        ()-> new SpecialityNotFoundException("we cant found the speciality")));
        return ResponseEntity.ok(doctorEntities);
    }


    @Transactional
    @Override
    public ResponseEntity<DoctorEntity> update(DoctorUpdateDTO doctorEntity) {
        DoctorEntity doctorToUpdate = doctorRepository.findById(doctorEntity.getId()).orElseThrow(() -> new DoctorNotFoundException("we cant found the doctor"));
        UserEntity userToUpdate = userRepository.findByDoctor(doctorToUpdate).orElseThrow(() -> new DoctorNotFoundException("We cant found a user associated with the doctor"));
        SpecialtyEntity specialtyEntity = specialityRepository.findByName(doctorEntity.getSpecialty()).orElseThrow(() -> new SpecialityNotFoundException("we cant found the speciality"));
        doctorToUpdate.setSpecialty(specialtyEntity);
        //Updating User base data
        userToUpdate.setName(doctorEntity.getName());
        userToUpdate.setEmail(doctorEntity.getEmail());
        userToUpdate.setLastname(doctorEntity.getLastname());
        userRepository.save(userToUpdate);
        doctorRepository.save(doctorToUpdate);
        return ResponseEntity.ok(doctorRepository.save(doctorToUpdate));
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
