package com.example.projeto.controller;

import com.example.projeto.dto.EmailUpdateRequest;
import com.example.projeto.dto.UserModelRequest;
import com.example.projeto.dto.UserModelResponse;
import com.example.projeto.dto.ErrorResponse;
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
    public ResponseEntity<UserModelResponse> buscarUsuarioPorId(@PathVariable Long id){
        //Entender esse return

//        Optional<UserModelResponse> userDto = userService.buscarPorId(id);
//        if (userDto.isPresent()){
//            return ResponseEntity.ok(userDto.get());
//        }
//        return ResponseEntity.notFound().build();

        return userService.buscarPorId(id)
                .map(userModelDto -> ResponseEntity.ok(userModelDto) )
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Inserir Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario Inserido Com Sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuario Invalido", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    @PostMapping()
    public ResponseEntity<UserModelResponse> adicionarUsuario(@RequestBody @Valid UserModelRequest user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.inserirUsuario(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserModelResponse> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid EmailUpdateRequest emailDto){
        UserModelResponse updatedUser = userService.atualizarEmail(id, emailDto.email());
        return ResponseEntity.ok(updatedUser);

     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
