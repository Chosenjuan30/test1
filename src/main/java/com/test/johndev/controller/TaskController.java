package com.test.johndev.controller;

import com.test.johndev.model.Task;
import com.test.johndev.service.TaskService;
import com.test.johndev.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // ✅ Get all tasks - 200 OK
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // ✅ Get task by ID - 200 OK or 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
        return ResponseEntity.ok(task); // 200 OK
    }

    // ✅ Create task - 201 Created
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED); // 201 Created
    }

    // ✅ Update task - 200 OK or 404 Not Found
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
        Task existingTask = taskService.getTaskById(id);
        if (existingTask == null) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask); // 200 OK
    }

    // ✅ Delete task - 204 No Content or 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Task existingTask = taskService.getTaskById(id);
        if (existingTask == null) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}