package com.todoTask.crud.repaso.services.interfaces;

import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IDoctorService {
    List<DoctorEntity> findByEspecialidad(SpecialtyEntity specialty);
    ResponseEntity<DoctorEntity> save(DoctorEntity doctorEntity);
    ResponseEntity<DoctorEntity> update(DoctorEntity doctorEntity);
    Page<DoctorEntity> findAll(Pageable pageable);
    Boolean delete(Long id);
}
