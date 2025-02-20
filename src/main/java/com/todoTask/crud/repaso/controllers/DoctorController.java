package com.todoTask.crud.repaso.controllers;

import com.todoTask.crud.repaso.dto.request.DoctorUpdateDTO;
import com.todoTask.crud.repaso.entities.DateEntity;
import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.services.interfaces.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    IDoctorService doctorService;

    @GetMapping("/speciality/{speciality}")
    public ResponseEntity<?> findBySpeciality(@PathVariable String speciality){
        return doctorService.findBySpeciality(speciality);
    }

    @PutMapping("/update")
    public ResponseEntity<DoctorEntity> update(@RequestBody DoctorUpdateDTO doctor){
        return doctorService.update(doctor);
    }

    @GetMapping()
    public ResponseEntity<Page<DoctorEntity>> findAll(Pageable pageable){
        return ResponseEntity.ok(doctorService.findAll(pageable));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable Long doctorId){
        return ResponseEntity.ok(doctorId);
    }
}
