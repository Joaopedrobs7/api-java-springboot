package com.example.projeto.dto;

import jakarta.validation.constraints.Email;

public record EmailUpdateRequest(
        @Email(message = "Email invalido")
        String email
) {
}
