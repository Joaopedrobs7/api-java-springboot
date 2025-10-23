package com.example.projeto.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class EmailUpdateDto {

    @Email(message = "Email invalido")
    private String email;
}
