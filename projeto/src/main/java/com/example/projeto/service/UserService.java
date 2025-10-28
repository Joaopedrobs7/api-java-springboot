package com.example.projeto.service;

import com.example.projeto.dto.UserModelRequest;
import com.example.projeto.dto.UserModelResponse;
import com.example.projeto.mapper.UserMapper;
import com.example.projeto.models.UserModel;
import com.example.projeto.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<UserModelResponse> buscarPorId(Long id){

        //Fazendo o get da entidade
        Optional<UserModel> userModel = userRepository.findById(id);

        //Instanciando novo dto
        UserModelResponse userModelResponse = mapper.toDto(userModel.get());

        return Optional.of(userModelResponse);
    }

    //Insert into User (nome,email) values({nome},{email})
    public UserModelResponse inserirUsuario(UserModelRequest userModelRequest){
        //Mapeando o Dto para a entidade UserModel
        UserModel userModel = mapper.toEntity(userModelRequest);
        UserModelResponse userModelResponse = mapper.toDto(userModel);
        userRepository.save(userModel);

        return userModelResponse;
    }

    //Delete from User where id Like = {id}
    public void deletarUsuario(long id){
        userRepository.deleteById(id);
    }

    //Update
    public UserModelResponse atualizarEmail(Long id, String email) {
        //Procura o usuario, se acha salva com as novas informacoes no 'user', e da um save encima desse user
        UserModel user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario Com id " + id + " nao encontrado"));
        user.setEmail(email);

        //Salva no banco
        userRepository.save(user);


        return mapper.toDto(user);
    }

}
