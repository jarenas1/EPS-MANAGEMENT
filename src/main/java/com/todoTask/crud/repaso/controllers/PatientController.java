package com.todoTask.crud.repaso.controllers;

import com.todoTask.crud.repaso.dto.request.patientDTOs.PatientUpdateDTO;
import com.todoTask.crud.repaso.entities.PatientEntity;
import com.todoTask.crud.repaso.exceptions.PatientNotFoundException;
import com.todoTask.crud.repaso.services.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    IPatientService iPatientService;

    @GetMapping("/{document}")
    public ResponseEntity<PatientEntity> getByDocument(@PathVariable String document){
        PatientEntity patientEntity = iPatientService.findByDocument(document).orElseThrow(()-> new PatientNotFoundException("We cant found the patient"));

        return ResponseEntity.ok(patientEntity);
    }


    @PutMapping("/update")
    public  ResponseEntity<PatientEntity> update(@RequestBody PatientUpdateDTO patientEntity){
        return iPatientService.update(patientEntity);
    }

    @GetMapping()
    public ResponseEntity<Page<PatientEntity>> findAll(Pageable pageable){
        return ResponseEntity.ok(iPatientService.findAll(pageable));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        iPatientService.delete(id);
        return ResponseEntity.ok(id);
    }
}
