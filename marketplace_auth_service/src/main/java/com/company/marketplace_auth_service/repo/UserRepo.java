package com.company.marketplace_auth_service.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UserRepo extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByUsername(String username);

    List<UserEntity> findByEmail(String email);
}
