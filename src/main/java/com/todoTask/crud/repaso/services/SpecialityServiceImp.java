package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import com.todoTask.crud.repaso.repositories.SpecialityRepository;
import com.todoTask.crud.repaso.services.interfaces.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialityServiceImp implements ISpecialityService {

    @Autowired
    SpecialityRepository specialityRepository;

    @Transactional
    @Override
    public List<SpecialtyEntity> findAll() {
        return specialityRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<SpecialtyEntity> findByName(String name) {

        return specialityRepository.findByName(name);
    }

    @Transactional
    @Override
    public ResponseEntity<SpecialtyEntity> save(SpecialtyEntity specialty) {
        SpecialtyEntity specialtyEntitySaved = specialityRepository.save(specialty);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(specialtyEntitySaved);
    }

    @Transactional
    @Override
    public Boolean delete(Long id) {
        Optional<SpecialtyEntity> optionalSpecialty = specialityRepository.findById(id);

        if(optionalSpecialty.isEmpty()){
            return false;
        }else{
            specialityRepository.delete(optionalSpecialty.get());
            return true;
        }
    }

    @Transactional
    @Override
    public ResponseEntity<SpecialtyEntity> update(SpecialtyEntity specialty) {
        SpecialtyEntity specialtyUpdated = specialityRepository.save(specialty);
        return ResponseEntity.status(HttpStatus.OK).body(specialtyUpdated);
    }
}
