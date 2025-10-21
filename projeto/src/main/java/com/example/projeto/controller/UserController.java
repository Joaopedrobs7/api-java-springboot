package com.example.projeto.controller;

import com.example.projeto.models.UserModel;
import com.example.projeto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {




    private final UserService userService;
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/users")
    public List<UserModel> listarUsuarios(){
        return userService.listarUsuarios();
    }

    @GetMapping("/users/{id}")
    public Optional<UserModel> listarUsuario(@PathVariable Long id){
        return userService.buscarPorId(id);
    }
}
