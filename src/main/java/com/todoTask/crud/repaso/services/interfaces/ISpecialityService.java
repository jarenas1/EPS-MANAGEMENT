package com.todoTask.crud.repaso.services.interfaces;

import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ISpecialityService {
    List<SpecialtyEntity> findAll();
    Optional<SpecialtyEntity> findByName(String name);
    ResponseEntity<SpecialtyEntity> save(SpecialtyEntity specialty);
    Boolean delete(Long id);
    ResponseEntity<SpecialtyEntity> update(SpecialtyEntity specialty);
    SpecialtyEntity findById(Long id);
}
