package com.pineapple.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pineapple.tasktracker.model.Issuerole;

@Repository
public interface IssueroleRepository extends JpaRepository<Issuerole, Long>{

}
