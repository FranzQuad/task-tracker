package com.pineapple.tasktracker.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationDto {
    private String email;
    private String name;
    private String password;
}