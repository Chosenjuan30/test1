package com.test.johndev.repository;

import com.test.johndev.model.Task; //
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // All CRUD methods are inherited from JpaRepository
}