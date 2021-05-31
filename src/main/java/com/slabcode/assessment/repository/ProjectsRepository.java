package com.slabcode.assessment.repository;

import com.slabcode.assessment.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Integer> {
    Project findByName(String name);
}
