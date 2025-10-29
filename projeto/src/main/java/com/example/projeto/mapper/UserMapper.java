package com.example.projeto.mapper;

import com.example.projeto.dto.UserModelRequest;
import com.example.projeto.dto.UserModelResponse;
import com.example.projeto.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //De dto request para entity, vamos ignorar o mapeamento do id, ja que esse é gerado na inserção
    @Mapping(target = "id", ignore = true)
    UserModel toEntity (UserModelRequest userModelRequest);

    UserModelResponse toDto (UserModel userModel);

    //Para o find all
    List<UserModelResponse> toDto (List<UserModel> userModels);

    //update, utilizando @MappingTarget entao ele altera a instancia existente
    void updateUserFromDto(UserModelRequest userModelRequest, @MappingTarget UserModel userModel);
}
