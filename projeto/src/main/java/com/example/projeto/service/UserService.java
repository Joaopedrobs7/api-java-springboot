package com.example.projeto.service;

import com.example.projeto.dto.UserModelDto;
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
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    //Select * From Users
    public List<UserModelDto> listarUsuarios(){
         List <UserModelDto> userModelDtos =  userRepository.findAll().stream().map(userModel -> new UserModelDto().toDto(userModel)).toList();


//         List <UserModel> userModels = userRepository.findAll();
//         List <UserModelDto> userModelDtoList = List.of();
//         for(int i = 0; i<userModels.size(); i++){
//             UserModel u = userModels.get(i);
//             UserModelDto uDto = new UserModelDto(u.getName(),u.getEmail());
//             userModelDtoList.add(uDto);
//         }
    
        return userModelDtos;
    }

    //Select * From Users Where id Like = {id}
    public Optional<UserModelDto> buscarPorId(Long id){

        //Fazendo o get da entidade
        Optional<UserModel> userModel = userRepository.findById(id);

        //Instanciando novo dto
        UserModelDto userModelDto = new UserModelDto().toDto(userModel.get());

        return Optional.of(userModelDto);
    }

    //Insert into User (nome,email) values({nome},{email})
    public UserModelDto inserirUsuario(UserModelDto userdto){
        //Mapeando o Dto para a entidade UserModel
        UserModel userModel = userdto.toEntity(userdto);

        userRepository.save(userModel);
        return userdto;
    }

    //Delete from User where id Like = {id}
    public void deletarUsuario(long id){
        userRepository.deleteById(id);
    }

    //Update
    public UserModel atualizarEmail(Long id, String email) {
        //Procura o usuario, se acha salva com as novas informacoes no 'user', e da um save encima desse user
        UserModel user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario Com id " + id + " nao encontrado"));
        user.setEmail(email);


//        //Vai salvar um novo ou dar Update?
//        userRepository.save(user);


        return userRepository.save(user);
    }

}
