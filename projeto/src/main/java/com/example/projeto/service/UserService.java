package com.example.projeto.service;

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
    public List<UserModel> listarUsuarios(){
        return userRepository.findAll();
    }

    //Select * From Users Where id Like = {id}
    public Optional<UserModel> buscarPorId(Long id){
        return userRepository.findById(id);
    }

    //Insert into User (nome,email) values({nome},{email})
    public UserModel inserirUsuario(UserModel user){
        return userRepository.save(user);
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
