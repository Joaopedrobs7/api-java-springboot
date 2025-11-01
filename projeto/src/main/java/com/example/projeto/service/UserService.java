package com.example.projeto.service;

import com.example.projeto.dto.UserModelRequest;
import com.example.projeto.dto.UserModelResponse;
import com.example.projeto.dto.UserUpdateRequest;
import com.example.projeto.exceptions.UserNotFoundException;
import com.example.projeto.mapper.UserMapper;
import com.example.projeto.models.UserModel;
import com.example.projeto.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    //Select * From Users
    public List<UserModelResponse> listarUsuarios(){
         List <UserModel> userModels =  userRepository.findAll();
         List <UserModelResponse> userModelResponses = mapper.toDto(userModels);


//         List <UserModel> userModels = userRepository.findAll();
//         List <UserModelDto> userModelDtoList = List.of();
//         for(int i = 0; i<userModels.size(); i++){
//             UserModel u = userModels.get(i);
//             UserModelDto uDto = new UserModelDto(u.getName(),u.getEmail());
//             userModelDtoList.add(uDto);
//         }
    
        return userModelResponses;
    }

    //Select * From Users Where id Like = {id}
    public UserModelResponse buscarPorId(Long id){

        return userRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("Usuario Não Encontrado -SERVICE-"));
    }

    //Insert into User (nome,email) values({nome},{email})
    public UserModelResponse inserirUsuario(UserModelRequest userModelRequest){
        //Mapeando o Dto para a entidade UserModel
        UserModel userModel = mapper.toEntity(userModelRequest);
        UserModel savedUser = userRepository.save(userModel);

        return mapper.toDto(savedUser);

    }

    //Delete from User where id Like = {id}
    public void deletarUsuario(long id){

        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("Usuario Não Encontrado");
        }

        userRepository.deleteById(id);
    }

    //Update
    public UserModelResponse atualizarEmail(Long id, UserUpdateRequest userUpdateRequest) {
        //Procura o usuario, se acha salva com as novas informacoes no 'user', e da um save encima desse user
        UserModel user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario Com id " + id + " nao encontrado"));

        //mapear para Entity
        if (userUpdateRequest.name() != null){
            user.setName(userUpdateRequest.name());
        }

        if(userUpdateRequest.email() != null){
            user.setEmail(userUpdateRequest.email());
        }

        if(userUpdateRequest.phone() != null){
            user.setPhone(userUpdateRequest.phone());
        }


        //mapear para entity
        //mapper.updateUserFromDto(userModelRequest,user);

        //Salva no banco
        userRepository.save(user);


        return mapper.toDto(user);
    }

}
