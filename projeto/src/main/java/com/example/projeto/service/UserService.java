package com.example.projeto.service;

import com.example.projeto.models.UserModel;
import com.example.projeto.repository.UserRepository;
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

    public List<UserModel> listarUsuarios(){
        return userRepository.findAll();
    }

    public Optional<UserModel> buscarPorId(Long id){
        return userRepository.findById(id);
    }
}
