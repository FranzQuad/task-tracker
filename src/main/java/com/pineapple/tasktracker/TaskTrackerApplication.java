package com.pineapple.tasktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TaskTrackerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskTrackerApplication.class, args);
	}
}
