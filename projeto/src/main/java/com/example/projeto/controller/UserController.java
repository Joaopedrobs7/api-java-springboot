package com.example.projeto.controller;

import com.example.projeto.models.UserModel;
import com.example.projeto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {




    private final UserService userService;
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping()
    public List<UserModel> listarUsuarios(){
        return userService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> buscarUsuarioPorId(@PathVariable Long id){
        //Entender esse return
        return userService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public UserModel adicionarUsuario(@RequestBody UserModel user){
        return userService.inserirUsuario(user);
    }

    @PutMapping()
    public Optional<UserModel> atualizarUsuario(@RequestBody UserModel user){
        return userService.atualizarEmail(user.getId(), user.getEmail());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
