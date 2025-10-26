package com.example.projeto.controller;

import com.example.projeto.dto.EmailUpdateDto;
import com.example.projeto.dto.UserModelDto;
import com.example.projeto.models.UserModel;
import com.example.projeto.records.ErrorResponse;
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
import java.util.Optional;

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

    public ResponseEntity<List<UserModelDto>> listarUsuarios(){
        return ResponseEntity.ok(userService.listarUsuarios());

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModelDto> buscarUsuarioPorId(@PathVariable Long id){
        //Entender esse return

        Optional<UserModelDto> userDto = userService.buscarPorId(id);
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
    public ResponseEntity<UserModelDto> adicionarUsuario(@RequestBody @Valid UserModelDto user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.inserirUsuario(user));
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
