package com.pineapple.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pineapple.tasktracker.model.Issuetype;

@Repository
public interface IssuetypeRepository extends JpaRepository<Issuetype, Long>{

}
