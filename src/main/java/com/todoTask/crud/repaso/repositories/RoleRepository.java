package com.todoTask.crud.repaso.repositories;


import com.todoTask.crud.repaso.entities.RoleEntity;
import com.todoTask.crud.repaso.tools.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findRoleByRoleEnum(RoleEnum roleName);
}
