package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import com.todoTask.crud.repaso.repositories.SpecialityRepository;
import com.todoTask.crud.repaso.services.interfaces.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class SpecialityServiceImp implements ISpecialityService {

    @Autowired
    SpecialityRepository specialityRepository;

    @Override
    public List<SpecialtyEntity> findAll() {
        return specialityRepository.findAll();
    }

    @Override
    public Optional<SpecialtyEntity> findByName(String name) {
        return specialityRepository.findByName(name);
    }

    @Override
    public ResponseEntity<SpecialtyEntity> save(SpecialtyEntity specialty) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Optional<SpecialtyEntity> update(SpecialtyEntity specialty) {
        return Optional.empty();
    }
}
