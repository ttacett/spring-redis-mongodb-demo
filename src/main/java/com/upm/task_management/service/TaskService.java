package com.upm.task_management.service;

import com.upm.task_management.entity.Task;
import com.upm.task_management.repository.TaskRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Cacheable(value = "tasks", key = "#id")
    public Task getTask(String id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setSource("Database");
        }
        return task;
    }

    @CacheEvict(value = "tasks", key = "#task.id")
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @CacheEvict(value = "tasks", key = "#task.id")
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @CacheEvict(value = "tasks", key = "#id")
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}
