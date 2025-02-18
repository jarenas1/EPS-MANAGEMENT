package com.todoTask.crud.repaso.services.interfaces;

import com.todoTask.crud.repaso.dto.request.DoctorCreateDTO;
import com.todoTask.crud.repaso.dto.request.DoctorUpdateDTO;
import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IDoctorService {
    ResponseEntity<?> findBySpeciality(Long idSpecialty);
//    ResponseEntity<DoctorEntity> save(DoctorCreateDTO doctorEntity);
    ResponseEntity<DoctorEntity> update(DoctorUpdateDTO doctorEntity);
    Page<DoctorEntity> findAll(Pageable pageable);
    Boolean delete(Long id);
}
