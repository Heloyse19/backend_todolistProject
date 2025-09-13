package com.projeto.todolist.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    
    private String descricao;
    private boolean concluido;

    @Column(name = "taskCr", updatable = false) //Registrar a criação da task
    private LocalDateTime taskCr;

    @Column(name = "taskAt") //Atualizar a data de modificação da task
    private LocalDateTime taskAt;

    //Sera executando antes de salvar no banco
    @PrePersist
    protected void onCreate(){
        taskCr = LocalDateTime.now();
        taskAt = LocalDateTime.now();
    }

    //Sera executado antes de atualizar no banco
    @PreUpdate
    protected void onUpdate(){
        taskAt = LocalDateTime.now();
    }
}
