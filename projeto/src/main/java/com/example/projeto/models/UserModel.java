package com.example.projeto.models;

import jakarta.persistence.*;
import lombok.*;

//Nao preciso usar @Getter e Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

//Mapear como entidade do banco
@Entity
@Table (name = "User")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String  name;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false,length = 20,unique = true)
    private String phone;

}
