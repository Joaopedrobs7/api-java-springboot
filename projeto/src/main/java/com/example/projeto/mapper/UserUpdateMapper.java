package com.example.projeto.mapper;

import com.example.projeto.dto.UserUpdateRequest;
import com.example.projeto.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserUpdateMapper  {
    void updateFromDto(UserUpdateRequest userUpdateRequest, @MappingTarget UserModel userModel);

}
