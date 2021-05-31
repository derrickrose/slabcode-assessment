package com.slabcode.assessment.facade;

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


    @Transactional
    public Task save(Task task) {
        return tasksRepository.save(task);
    }


    public Task findByName(String name) {
        return tasksRepository.findByName(name);
    }


    public Task findById(Integer id) {
        return tasksRepository.findById(id).orElse(null);
    }

    public Task update(Task task) {
        return tasksRepository.save(task);
    }

}
