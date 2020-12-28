package com.pineapple.tasktracker.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDto {
    private String description;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}