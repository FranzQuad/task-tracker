package com.pineapple.tasktracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pineapple.tasktracker.model.Projectparticipant;


@Repository
public interface ProjectparticipantRepository extends JpaRepository<Projectparticipant, Long>{
	List<Projectparticipant> findAllByUserBean_Id(Long userBean_Id);
}
