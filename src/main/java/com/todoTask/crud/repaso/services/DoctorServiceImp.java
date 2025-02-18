package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.dto.request.DoctorCreateDTO;
import com.todoTask.crud.repaso.entities.*;
import com.todoTask.crud.repaso.error_handler.DoctorNotFoundException;
import com.todoTask.crud.repaso.error_handler.PatientNotFoundException;
import com.todoTask.crud.repaso.error_handler.SpecialityNotFoundException;
import com.todoTask.crud.repaso.repositories.DoctorRepository;
import com.todoTask.crud.repaso.repositories.SpecialityRepository;
import com.todoTask.crud.repaso.repositories.UserRepository;
import com.todoTask.crud.repaso.services.interfaces.IDoctorService;
import com.todoTask.crud.repaso.tools.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public ResponseEntity<?> findBySpeciality(Long idSpecialty) {
        List<DoctorEntity> doctorEntities = doctorRepository.findBySpecialty(
                specialityRepository.findById(idSpecialty).orElseThrow(
                        ()-> new SpecialityNotFoundException("we cant found the speciality")));
        return ResponseEntity.ok(doctorEntities);
    }

//    @Transactional
//    @Override
//    public ResponseEntity<DoctorEntity> save(DoctorCreateDTO doctorEntity) {
//        //we've to create a user first to create the relation bw doctor and user
//        Set<RoleEntity> roles = new HashSet<>();
//        roles.add(RoleEntity);
//        UserEntity userEntity = UserEntity.builder()
//                .email(doctorEntity.getEmail())
//                .password(doctorEntity.getPassword())
//                .name(doctorEntity.getName())
//                .lastname(doctorEntity.getLastname())
//                .roles(roles)
//                .build();
//
//        SpecialtyEntity specialty = specialityRepository.findByName(doctorEntity.getSpecialty()).orElseThrow(()
//                -> new SpecialityNotFoundException("we cant found the speciality"));
//
//        DoctorEntity doctorSaved = doctorRepository.save(DoctorEntity.builder()
//                        .user(userEntity)
//                        .specialty(specialty)
//                .build());
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(doctorSaved);
//    }

    @Transactional
    @Override
    public ResponseEntity<DoctorEntity> update(DoctorEntity doctorEntity) {
        DoctorEntity doctorToUpdate = doctorRepository.findById(doctorEntity.getId()).orElseThrow(() -> new DoctorNotFoundException("we cant found the doctor"));
        doctorToUpdate.setId(doctorEntity.getId());
        doctorToUpdate.setShifts(doctorEntity.getShifts());
        doctorToUpdate.setSpecialty(doctorEntity.getSpecialty());
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
