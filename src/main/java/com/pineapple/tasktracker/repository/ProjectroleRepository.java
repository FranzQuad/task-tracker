package com.pineapple.tasktracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pineapple.tasktracker.model.Projectrole;


@Repository
public interface ProjectroleRepository extends JpaRepository<Projectrole, Long> {
}
