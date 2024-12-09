package com.todoTask.crud.repaso.controllers;

import com.todoTask.crud.repaso.dto.request.UserDTO;
import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.PatientEntity;
import com.todoTask.crud.repaso.entities.SpecialtyEntity;
import com.todoTask.crud.repaso.entities.UserEntity;
import com.todoTask.crud.repaso.services.JwtService;
import com.todoTask.crud.repaso.services.UserDetailsService;
import com.todoTask.crud.repaso.services.interfaces.*;
import com.todoTask.crud.repaso.tools.enums.RoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private IUserService usuarioService;

    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private ISpecialityService specialityService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new LoginResponse(roles,token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Credentials");
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody RegistroRequest registroRequest) {
        try {
            // Mapear request a entidad Usuario
            UserDTO user = new UserDTO();
            user.setUsername(registroRequest.getUsername());
            user.setPassword(registroRequest.getPassword());
            user.setEmail(registroRequest.getEmail());

            user.getRoles().add(RoleUser.ROLE_PATIENT);

            if (user.getAdmin()){
                user.getRoles().add(RoleUser.ROLE_ADMIN);
            }
            if(user.getDoctor()){
                user.getRoles().add(RoleUser.ROLE_DOCTOR);
            }

            UserEntity userEntity= new UserEntity(user.getActive(), user.getEmail(), user.getPassword(), user.getRoles(), user.getUsername());

            UserEntity registredUser = usuarioService.save(userEntity);

            //CHECK IF USER IS DOCTOR OR PATIENT AND CREATING THE RELATED INSTANCES
            if (registredUser.getRoles().contains(RoleUser.ROLE_DOCTOR)){
                SpecialtyEntity specialtyEntity = specialityService.findById(user.getSpecialty_id());
                DoctorEntity doctor = new DoctorEntity(user.getLastname(), user.getName(), specialtyEntity,registredUser);
                return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.save(doctor));
            }
            if (registredUser.getRoles().contains(RoleUser.ROLE_PATIENT)){
                PatientEntity patient = new PatientEntity(user.getDocument(), user.getLastname(), user.getName(), userEntity);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error in register: " + e.getMessage());
        }
    }
}
class LoginRequest {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

class RegistroRequest {
    private String username;
    private String password;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

class LoginResponse {
    private String token;
    private List<String> roles;

    public LoginResponse(List<String> roles, String token) {
        this.roles = roles;
        this.token = token;
    }

    public LoginResponse() {
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}