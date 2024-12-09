package com.todoTask.crud.repaso.services;

import com.todoTask.crud.repaso.entities.UserEntity;
import com.todoTask.crud.repaso.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("We cant find the user"));

        return new com.todoTask.crud.repaso.services.interfaces.UserDetails(userEntity);
    }
}
