package com.todoTask.crud.repaso.controllers;

import com.todoTask.crud.repaso.dto.request.DateWOStatus;
import com.todoTask.crud.repaso.entities.DateEntity;
import com.todoTask.crud.repaso.services.DateServiceImp;
import com.todoTask.crud.repaso.services.interfaces.IDateService;
import com.todoTask.crud.repaso.tools.enums.DateStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/dates")
public class DateController {

    @Autowired
    IDateService dateServiceImp;

    @GetMapping("/between/{id}/{start}/{end}")
    public ResponseEntity<List<DateEntity>> findByDoctorAndDateTimeBetween(@PathVariable Long id, @PathVariable LocalDateTime start, @PathVariable LocalDateTime end){
        List<DateEntity> dates = dateServiceImp.findByDoctorAndDateTimeBetween(id,start,end);
        return ResponseEntity.ok(dates);
    }

    @GetMapping("/patient/{id}/{status}")
    public ResponseEntity<List<DateEntity>> findByPatientAndStatus(@PathVariable Long id, @PathVariable DateStatus status){
        List<DateEntity> dateEntities = dateServiceImp.findByPatientAndStatus(id,status);
        return ResponseEntity.ok(dateEntities);
    }

    @GetMapping("/doctor/{id}/{status}")
    public ResponseEntity<List<DateEntity>> findByDoctorAndStat(@PathVariable Long id, @PathVariable DateStatus status){
        List<DateEntity> dates = dateServiceImp.findByDoctorAndStatus(id,status);
        return ResponseEntity.ok(dates);
    }

    @PatchMapping("/reshudel/{id}")
    public ResponseEntity<DateEntity> rescheduleDate(@PathVariable Long id,  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newDate){
        return dateServiceImp.rescheduleDate(id, newDate);
    }

    @PostMapping("/create")
    public ResponseEntity<DateEntity> create(@RequestBody DateWOStatus dateWOStatus){
        return dateServiceImp.save(dateWOStatus);
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity<DateEntity> cancelDate (@PathVariable Long id){
        return dateServiceImp.cancelDate(id);
    }

    @PutMapping("/update")
    public ResponseEntity<DateEntity> update(@RequestBody DateEntity dateEntity){
        return dateServiceImp.update(dateEntity);
    }

    @PatchMapping("/done/{id}")
    public ResponseEntity<DateEntity> markAsDone(@PathVariable Long id){
        return dateServiceImp.putDateAsDone(id);
    }

    @GetMapping
    public ResponseEntity<Page<DateEntity>> findAll (Pageable pageable){
        Page<DateEntity> datesPaginated = dateServiceImp.findAll(pageable);
        return ResponseEntity.ok(datesPaginated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        dateServiceImp.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
