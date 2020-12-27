package com.pineapple.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pineapple.tasktracker.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
