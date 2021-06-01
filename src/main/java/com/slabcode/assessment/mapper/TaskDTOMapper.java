package com.slabcode.assessment.mapper;

import com.slabcode.assessment.dto.TaskDTO;
import com.slabcode.assessment.entity.Task;
import com.slabcode.assessment.exception.CustomException;
import com.slabcode.assessment.facade.ProjectsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskDTOMapper {

    @Autowired
    private ProjectsFacade projectsFacade;

    public TaskDTO fromTask(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        //taskDTO.setCreationDate(task.getCreationDate());
        //taskDTO.setTerminationDate(task.getTerminationDate());
        taskDTO.setExecutionDate(task.getExecutionDate());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setProjectId(task.getProject().getId());
        return taskDTO;
    }

    public Task toTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        // task.setCreationDate(taskDTO.getCreationDate());
        task.setExecutionDate(taskDTO.getExecutionDate());
        //task.setTerminationDate(taskDTO.getTerminationDate());
        task.setStatus(taskDTO.getStatus());
        if (projectsFacade.existsById(taskDTO.getProjectId())) {
            task.setProject(projectsFacade.findById(taskDTO.getProjectId()));
        } else {
            throw new CustomException("Project not found", HttpStatus.NOT_FOUND);
        }
        return task;
    }
}
