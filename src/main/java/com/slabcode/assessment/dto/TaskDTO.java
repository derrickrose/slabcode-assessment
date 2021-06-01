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
    private Integer id;
    private String name;
    private String description;
    //private Calendar creationDate;
    private Calendar executionDate;
    //private Calendar terminationDate;
    private ProgressStatus status;
    private Integer projectId;
}
