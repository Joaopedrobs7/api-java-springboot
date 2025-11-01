package com.example.projeto.controller;

import com.example.projeto.dto.ErrorResponse;
import com.example.projeto.dto.UserModelRequest;
import com.example.projeto.dto.UserModelResponse;
import com.example.projeto.dto.UserUpdateRequest;
import com.example.projeto.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Lista todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping()

    public ResponseEntity<List<UserModelResponse>> listarUsuarios(){
        return ResponseEntity.ok(userService.listarUsuarios());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id){
        UserModelResponse userModelResponse =  userService.buscarPorId(id);
        return ResponseEntity.ok(userModelResponse);
    }

    @Operation(summary = "Inserir Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario Inserido Com Sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuario Invalido", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    @PostMapping()
    public ResponseEntity<UserModelResponse> adicionarUsuario(@RequestBody @Valid UserModelRequest user){
        //Caso n passe no validation, o proprio validation lanca a exception /MethodArgumentNotValidException/
        //no restController advice, fazemos o binding de cada campo onde faltou validacao.

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.inserirUsuario(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserModelResponse> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid UserUpdateRequest userUpdateRequest){
        UserModelResponse updatedUser = userService.atualizarEmail(id,userUpdateRequest);
        return ResponseEntity.ok(updatedUser);

     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
