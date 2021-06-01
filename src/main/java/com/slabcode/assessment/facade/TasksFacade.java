package com.slabcode.assessment.facade;

import com.slabcode.assessment.entity.Task;
import com.slabcode.assessment.repository.TasksRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public boolean existsById(Integer id) {
        return tasksRepository.existsById(id);
    }

    @Transactional
    public void deleteById(Integer id) {
        tasksRepository.deleteById(id);
    }

    public List<Task> findAll() {//TODO paginate nex time
        return tasksRepository.findAll();
    }

    public Set<Task> getNotDoneTasksByProjectId(Integer projectId) {
        return tasksRepository.getNotDoneTasksByProjectId(projectId).orElse(new HashSet<>());
    }

}
