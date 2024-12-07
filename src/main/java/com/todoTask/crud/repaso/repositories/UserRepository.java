package com.todoTask.crud.repaso.repositories;

import com.todoTask.crud.repaso.entities.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
