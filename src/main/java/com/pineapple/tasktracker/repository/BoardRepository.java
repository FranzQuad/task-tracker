package com.pineapple.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pineapple.tasktracker.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	
}
