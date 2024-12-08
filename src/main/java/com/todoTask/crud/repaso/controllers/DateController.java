package com.todoTask.crud.repaso.controllers;

import com.todoTask.crud.repaso.services.DateServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dates")
public class DateController {
    @Autowired
    DateServiceImp dateServiceImp;

    @GetMapping("/between/{id}/{start}/{end}")
    public ResponseEntity<?> findByDoctorAndDateTimeBetween(@PathVariable ){

    }
}
