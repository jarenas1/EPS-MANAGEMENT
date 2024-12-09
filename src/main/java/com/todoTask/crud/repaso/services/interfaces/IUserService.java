package com.todoTask.crud.repaso.services.interfaces;

import com.todoTask.crud.repaso.entities.UserEntity;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    UserEntity save(UserEntity user);
    ResponseEntity<UserEntity> auth(String username, String password);

}
