package com.todoTask.crud.repaso.repositories;

import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialityRepository extends JpaRepository<SpecialtyEntity, Long> {
   Optional<SpecialtyEntity> findByName(String name);
}
