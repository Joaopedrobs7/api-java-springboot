package com.example.projeto.dto;

import com.example.projeto.models.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModelDto {
    @Size(min = 3, max = 100, message = "Nome invalido")
    private String name;

    @Email(message = "Email invalido")
    private String email;

    public  UserModel toEntity (UserModelDto userModelDto){
        UserModel userModel = new UserModel();
        userModel.setName(userModelDto.getEmail());
        userModel.setName(userModelDto.getName());

        return userModel;
    }

    public UserModelDto toDto (UserModel userModel){
        return new UserModelDto(userModel.getName(), userModel.getEmail());
    }
}

