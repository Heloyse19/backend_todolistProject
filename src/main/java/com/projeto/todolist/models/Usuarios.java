package com.projeto.todolist.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Data
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 20)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    @Email
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 8)
    private String senha;

    @Column(name = "usuarioCr", updatable = false)
    private LocalDateTime userCr;

}
