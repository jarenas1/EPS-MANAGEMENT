package com.todoTask.crud.repaso.controllers;

import com.todoTask.crud.repaso.dto.request.auth.*;
import com.todoTask.crud.repaso.dto.response.AuthResponse;
import com.todoTask.crud.repaso.services.UserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthtenticationController {

    @Autowired
    private UserDetailsService userDetailService;

    @PostMapping("/sign-up/admin")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid AuthCreateUserAdmin authCreateUser){
        return new ResponseEntity<>(userDetailService.createUserAdmin(authCreateUser), HttpStatus.CREATED);
    }

    @PostMapping("/sign-up/doctor")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid AuthCreateUserDoctor authCreateUser){
        return new ResponseEntity<>(userDetailService.createUserDoctor(authCreateUser), HttpStatus.CREATED);
    }

    @PostMapping("/sign-up/patient")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid AuthCreateUserPatient authCreateUser){
        return new ResponseEntity<>(userDetailService.createUserPatient(authCreateUser), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity(userDetailService.loginUser(userRequest), HttpStatus.OK);
    }
}
