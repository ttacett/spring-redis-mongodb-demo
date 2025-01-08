package com.upm.task_management.controller;

import com.upm.task_management.entity.Task;
import com.upm.task_management.service.TaskService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final CacheManager cacheManager;

    // Manual constructor for dependency injection
    public TaskController(TaskService taskService, CacheManager cacheManager) {
        this.taskService = taskService;
        this.cacheManager = cacheManager;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable String id) {
        Task resultTask = null;
        String source = "Database";

        // Trying cache
        Cache cache = cacheManager.getCache("tasks");
        if (cache != null) {
            resultTask = cache.get(id, Task.class);
            if (resultTask != null) {
                source = "Cache";
            }
        }

        // Trying service if not in cache
        if (resultTask == null) {
            resultTask = taskService.getTask(id);
        }

        // Return result if found
        if (resultTask != null) {
            resultTask.setSource(source);
            return ResponseEntity.ok(resultTask);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        Task existingTask = taskService.getTask(id);
        if (existingTask == null) {
            return ResponseEntity.notFound().build();
        }

        updatedTask.setId(id);
        Task savedTask = taskService.updateTask(updatedTask);

        return ResponseEntity.ok(savedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }
}
