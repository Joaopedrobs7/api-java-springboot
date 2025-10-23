package com.example.projeto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModelDto {
    @Size(min = 3, max = 100, message = "Nome invalido")
    private String name;

    @Email(message = "Email invalido")
    private String email;
}
