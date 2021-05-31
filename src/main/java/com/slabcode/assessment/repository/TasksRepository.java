package com.slabcode.assessment.repository;

import com.slabcode.assessment.entity.Project;
import com.slabcode.assessment.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Task, Integer> {
    Task findByName(String name);

    Task save(Project project);

    Task findById(Long id);
}
