package com.harshni.taskmanager.controller;
import com.harshni.taskmanager.model.Task;
import com.harshni.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "https://harshni-task-manager.netlify.app/")


public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks(){
        return taskRepository.findAll() ;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskRepository.save(task) ;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,@RequestBody Task updatedTask){
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(taskOptional.isPresent()){
            Task task=taskOptional.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setDeadline(updatedTask.getDeadline());
            task.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(task);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskRepository.deleteById(id);
    }

}
