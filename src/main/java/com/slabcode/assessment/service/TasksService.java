package com.slabcode.assessment.service;

import com.slabcode.assessment.entity.Task;
import com.slabcode.assessment.facade.TasksFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasksService {

    private TasksFacade tasksFacade;

    @Autowired
    public TasksService(TasksFacade tasksFacade) {
        this.tasksFacade = tasksFacade;
    }

    public Task findByName(String name) {
        return tasksFacade.findByName(name);
    }

    public Task save(Task task) {
        return tasksFacade.save(task);
    }

    public Task findById( Integer id) {
        return tasksFacade.findById(id);
    }
}
