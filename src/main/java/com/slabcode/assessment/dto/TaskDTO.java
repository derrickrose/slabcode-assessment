package com.slabcode.assessment.dto;

import com.slabcode.assessment.constant.ProgressStatus;
import com.slabcode.assessment.entity.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {
    private  Integer id;
    private String name;
    private String description;
    private Calendar creationDate;
    private Calendar terminationDate;
    private ProgressStatus status;

    public static TaskDTO fromTask(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setCreationDate(task.getCreationDate());
        taskDTO.setTerminationDate(task.getTerminationDate());
        taskDTO.setStatus(task.getStatus());
        return taskDTO;
    }

    public Task toTask() {
        Task task = new Task();
        task.setId(this.id);
        task.setName(this.name);
        task.setDescription(this.description);
        task.setCreationDate(this.creationDate);
        task.setTerminationDate(this.terminationDate);
        task.setStatus(this.status);
        return task;
    }
}
