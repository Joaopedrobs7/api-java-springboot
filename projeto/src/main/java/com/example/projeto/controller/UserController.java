package com.example.projeto.controller;

import com.example.projeto.dto.EmailUpdateDto;
import com.example.projeto.dto.UserModelDto;
import com.example.projeto.models.UserModel;
import com.example.projeto.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {




    //RequiredArgsConstructor faz a instancia desse cara
    private final UserService userService;
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping()
    public List<UserModel> listarUsuarios(){
        return userService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModelDto> buscarUsuarioPorId(@PathVariable Long id){
        //Entender esse return
        return userService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public UserModel adicionarUsuario(@RequestBody @Valid UserModelDto user){
        return userService.inserirUsuario(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserModel> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid EmailUpdateDto emailDto){
        UserModel updatedUser = userService.atualizarEmail(id,emailDto.getEmail());
        return ResponseEntity.ok(updatedUser);

     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
