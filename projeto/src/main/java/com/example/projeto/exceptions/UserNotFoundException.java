package com.example.projeto.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String mensagem) {
        super(mensagem);
    }
}
