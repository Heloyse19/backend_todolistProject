package com.projeto.todolist.services;

import com.projeto.todolist.models.Tasks;
import com.projeto.todolist.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TasksRepository taskRepository;

    @Autowired
    public TaskService(TasksRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Tasks> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Tasks> getTasksById(Long id){
        return taskRepository.findById(id);
    }

    public Tasks createTask(Tasks task){
        return taskRepository.save(task);
    }

    public Tasks updateTask(Long id, Tasks taskUpdated){
        Tasks tasks = taskRepository.findById(id).orElseThrow();

        tasks.setTitulo(taskUpdated.getTitulo());
        tasks.setDescricao(taskUpdated.getDescricao());
        tasks.setConcluido(taskUpdated.isConcluido());

        return taskRepository.save(tasks);
    }

    public void deletedTask(Long id){
        Tasks tasks = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(tasks);
    }

    public Tasks saveTask(Tasks task){
        return taskRepository.save(task);
    }
}
