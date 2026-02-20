package com.test.johndev;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public class TaskApplication {
    @Entity
    @Table(name = "tasks")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor

    public class Task {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String title;

        private String description;

        private Boolean completed = false;

        @CreationTimestamp
        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;
    }
}
