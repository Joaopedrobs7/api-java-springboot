package com.example.projeto.dto;

public record ErrorResponse(
        String timestamp,
        int status,
        String message
) {}
