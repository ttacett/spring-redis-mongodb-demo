package com.upm.task_management.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "tasks")
public class Task implements Serializable {
    @Id
    private String id;
    private String title;
    private String description;
    private boolean completed;

    private transient String source;
}
