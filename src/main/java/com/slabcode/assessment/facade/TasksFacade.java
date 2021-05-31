package com.slabcode.assessment.facade;

import com.slabcode.assessment.entity.Project;
import com.slabcode.assessment.entity.Task;
import com.slabcode.assessment.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class TasksFacade {
    private TasksRepository tasksRepository;

    @Autowired
    TasksFacade(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Task findByName(String name) {
        return tasksRepository.findByName(name);
    }

    @Transactional
    public Task save(Task task) {
        return tasksRepository.save(task);
    }


    public Task findById(Long id) {
        return tasksRepository.findById(id);
    }

}
