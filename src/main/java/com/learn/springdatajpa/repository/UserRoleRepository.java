package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
