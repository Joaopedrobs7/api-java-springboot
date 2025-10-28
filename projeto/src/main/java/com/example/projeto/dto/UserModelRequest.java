package com.example.projeto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserModelRequest(
        @Size(min = 3, max = 100, message = "Nome invalido")
        String name,
        @Email(message = "Email invalido")
        String email) {
}
