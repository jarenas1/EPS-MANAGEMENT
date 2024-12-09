package com.todoTask.crud.repaso.controllers;

import com.todoTask.crud.repaso.entities.DateEntity;
import com.todoTask.crud.repaso.services.DateServiceImp;
import com.todoTask.crud.repaso.tools.enums.DateStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/dates")
public class DateController {
    @Autowired
    DateServiceImp dateServiceImp;

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

    @PatchMapping("/reshudel/{id}/{date}")
    public ResponseEntity<DateEntity> rescheduleDate(@PathVariable Long id, @PathVariable LocalDateTime newDate){
        return dateServiceImp.rescheduleDate(id, newDate);
    }


}
