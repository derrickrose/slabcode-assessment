package com.slabcode.assessment.repository;

import com.slabcode.assessment.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.Optional;
import java.util.Set;


@Repository
public interface TasksRepository extends JpaRepository<Task, Integer> {
    Task findByName(String name);

    boolean existsById(Integer id);

    @Query("SELECT t FROM Task t JOIN t.project tp " +
            "WHERE tp.id=:projectId " +
            "AND t.status <> 'DONE' ")
    Optional<Set<Task>> getNotDoneTasksByProjectId(@Param("projectId") Integer projectId);

}
