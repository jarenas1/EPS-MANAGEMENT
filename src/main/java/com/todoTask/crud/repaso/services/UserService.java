package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.entities.UserEntity;
import com.todoTask.crud.repaso.error_handler.InvalidCredentialsException;
import com.todoTask.crud.repaso.error_handler.UserExsistentException;
import com.todoTask.crud.repaso.repositories.UserRepository;
import com.todoTask.crud.repaso.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserEntity save(UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserExsistentException("the username is already in use");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserExsistentException("The email is already registred");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<UserEntity> auth(String username, String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Wrong password");
        }

        return ResponseEntity.ok(user);
    }
}
