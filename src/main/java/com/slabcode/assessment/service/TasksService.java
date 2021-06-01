package com.slabcode.assessment.service;

import com.slabcode.assessment.constant.ProgressStatus;
import com.slabcode.assessment.entity.Project;
import com.slabcode.assessment.entity.Task;
import com.slabcode.assessment.exception.CustomException;
import com.slabcode.assessment.facade.ProjectsFacade;
import com.slabcode.assessment.facade.TasksFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TasksService {

    private TasksFacade tasksFacade;

    private ProjectsFacade projectsFacade;

    @Autowired
    public TasksService(TasksFacade tasksFacade, ProjectsFacade projectsFacade) {
        this.tasksFacade = tasksFacade;
        this.projectsFacade = projectsFacade;
    }

    public Task findByName(String name) {
        return tasksFacade.findByName(name);
    }

    public Task save(Task task) {
        if (isExecutionDateValid(task, task.getProject())) {
            return tasksFacade.save(task);
        } else {
            throw new CustomException("Task executionDate is not valid", HttpStatus.BAD_REQUEST);
        }
    }

    public Task findById(Integer id) {
        return tasksFacade.findById(id);
    }

    public Task update(Task task) {
        if (tasksFacade.existsById(task.getId())) {
            Task existingTask = tasksFacade.findById(task.getId());
            existingTask.setName(task.getName());
            existingTask.setDescription(task.getDescription());
            if ((!existingTask.getExecutionDate().equals(task.getExecutionDate()))
                    && isExecutionDateValid(task, task.getProject())) {
                existingTask.setExecutionDate(task.getExecutionDate());
            }
            return tasksFacade.save(existingTask);
        } else {
            throw new CustomException("Project does not exist", HttpStatus.NOT_FOUND);
        }
    }


    public void deteleById(Integer id) {
        if (tasksFacade.existsById(id)) {
            tasksFacade.deleteById(id);
        } else {
            throw new CustomException("Project doest not exist", HttpStatus.NOT_FOUND);
        }
    }

    boolean isExecutionDateValid(Task task, Project project) {
        return isAfterProjectCreationDate(task, project) &&
                isBeforeProjectTerminationDate(task, project);
    }

    boolean isAfterProjectCreationDate(Task task, Project project) {
        //because will set a date on save on db
        return task.getExecutionDate() == null || task.getExecutionDate().after(project.getCreationDate());
    }

    boolean isBeforeProjectTerminationDate(Task task, Project project) {
        return project.getTerminationDate() == null ||
                (task.getExecutionDate() != null && task.getExecutionDate().before(project.getTerminationDate()));
    }

    public Task updateStatus(Integer id, String status) {
        if (!tasksFacade.existsById(id)) {
            throw new CustomException("Task does not exist", HttpStatus.NOT_FOUND);
        }
        if (ProgressStatus.isValid(status)) {
            Task task = tasksFacade.findById(id);
            task.setStatus(ProgressStatus.fromRaw(status));
            return tasksFacade.save(task);
        } else {
            throw new CustomException("Invalid status " + status, HttpStatus.BAD_REQUEST);
        }
    }

    public List<Task> getNotDoneTasksByProjectId(Integer projectId) {
        return tasksFacade.getNotDoneTasksByProjectId(projectId);
    }
}
