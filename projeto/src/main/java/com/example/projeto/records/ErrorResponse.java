package com.example.projeto.records;

public record ErrorResponse(
        String timestamp,
        int status,
        String message
) {}
