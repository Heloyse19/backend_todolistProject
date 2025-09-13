package com.projeto.todolist.controllers;

import com.projeto.todolist.models.Tasks;
import com.projeto.todolist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TasksController {

    private final TaskService taskService;

    @Autowired
    public TasksController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public List<Tasks> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTasksById(@PathVariable Long id){
        return taskService.getTasksById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tasks createTask(@RequestBody Tasks taskC){ //tasckC = task criada
        return taskService.createTask(taskC);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tasks> updateTask(@PathVariable Long id, @RequestBody Tasks taskDetails){
        try{
            Tasks taskUpdated = taskService.updateTask(id, taskDetails);
            return ResponseEntity.ok(taskUpdated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deteleTask(@PathVariable Long id){
        try{
            taskService.deletedTask(id);
            return ResponseEntity.ok(id);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
