package com.todoTask.crud.repaso.controllers;

import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import com.todoTask.crud.repaso.error_handler.SpecialityNotFoundException;
import com.todoTask.crud.repaso.services.interfaces.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialities")
public class SpecialityController {

    @Autowired
    ISpecialityService specialityService;

    @GetMapping
    public ResponseEntity<List<SpecialtyEntity>> findAll(){
        return ResponseEntity.ok(specialityService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<SpecialtyEntity> findByName(@PathVariable String name){
        SpecialtyEntity specialty = specialityService.findByName(name).orElseThrow(()-> new SpecialityNotFoundException("we cant found the speciality"));
        return ResponseEntity.ok(specialty);
    }

    @PostMapping
    public ResponseEntity<SpecialtyEntity> create(@RequestBody SpecialtyEntity specialty){
        return specialityService.save(specialty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        specialityService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PutMapping()
    public ResponseEntity<SpecialtyEntity> update(@RequestBody SpecialtyEntity specialty){
        return specialityService.update(specialty);
    }
}
