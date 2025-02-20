package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.dto.request.auth.*;
import com.todoTask.crud.repaso.dto.response.AuthResponse;
import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.PatientEntity;
import com.todoTask.crud.repaso.entities.UserEntity;
import com.todoTask.crud.repaso.repositories.*;
import com.todoTask.crud.repaso.tools.enums.RoleEnum;
import com.todoTask.crud.repaso.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private SpecialityRepository specialityRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("we cant found the user with email: " + email));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleEnum().name()));

            //putting permissions into list
            user.getRoles().stream()
                    .flatMap(roles -> role.getPermissionList().stream())
                    .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));
        });

        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    public Authentication authenticate(String email, String password) {
        UserDetails userDetails = this.loadUserByUsername(email);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User " + email + " doesn't exist.");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("bad password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest){

        String email = authLoginRequest.email();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(email,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(email, "user authenticated", token, true);

        return authResponse;
    }

    public AuthResponse createUserAdmin(AuthCreateUserAdmin authCreateUser){
        String email = authCreateUser.email();
        String password = authCreateUser.password();

        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(Set.of(roleRepository.findRoleByRoleEnum(RoleEnum.ROLE_ADMIN).get()))
                .lastname(authCreateUser.lastname())
                .name(authCreateUser.name())
                .build();

        UserEntity userCreated = userRepository.save(userEntity);

        AuthResponse userLogged = loginUser(new AuthLoginRequest(email, password));

        return new AuthResponse(userCreated.getEmail(), "user created", userLogged.token(), true);
    }

    public AuthResponse createUserDoctor(AuthCreateUserDoctor authCreateUser){
        String email = authCreateUser.email();
        String password = authCreateUser.password();

        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(Set.of(roleRepository.findRoleByRoleEnum(RoleEnum.ROLE_DOCTOR).get()))
                .lastname(authCreateUser.lastname())
                .name(authCreateUser.name())
                .build();

        UserEntity userSaved = userRepository.save(userEntity);

        //create doctor associated with user
        DoctorEntity doctorEntity = DoctorEntity.builder()
                .user(userSaved)
                .specialty(specialityRepository.findByName(authCreateUser.specialty()).orElseThrow(() -> new BadCredentialsException("specialty doesn't exist")))
                .build();

        doctorRepository.save(doctorEntity);

        AuthResponse userLogged = loginUser(new AuthLoginRequest(email, password));

        return new AuthResponse(userSaved.getEmail(), "user created", userLogged.token(), true);
    }

    public AuthResponse createUserPatient(AuthCreateUserPatient authCreateUser){
        String email = authCreateUser.email();
        String password = authCreateUser.password();

        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(Set.of(roleRepository.findRoleByRoleEnum(RoleEnum.ROLE_PATIENT).get()))
                .lastname(authCreateUser.lastname())
                .name(authCreateUser.name())
                .build();

        UserEntity userSaved = userRepository.save(userEntity);

        PatientEntity patientEntity = PatientEntity.builder()
                .user(userSaved)
                .document(authCreateUser.cedula())
                .build();

        patientRepository.save(patientEntity);

        AuthResponse userLogged = loginUser(new AuthLoginRequest(email, password));

        return new AuthResponse(userSaved.getEmail(), "user created", userLogged.token(), true);
    }
}
