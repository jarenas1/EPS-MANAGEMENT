package com.todoTask.crud.repaso.repositories;


import com.todoTask.crud.repaso.entities.RoleEntity;

import java.util.Optional;

public interface RoleRepository {
    Optional<RoleEntity> findRoleByName(String roleName);
}
