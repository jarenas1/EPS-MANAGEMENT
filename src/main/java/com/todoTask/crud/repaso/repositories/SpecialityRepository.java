package com.todoTask.crud.repaso.repositories;

import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialityRepository extends JpaRepository<SpecialtyEntity, Long> {
   Optional<SpecialtyEntity> findByName(String name);
}
