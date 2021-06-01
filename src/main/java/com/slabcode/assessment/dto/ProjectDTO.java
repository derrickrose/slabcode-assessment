package com.slabcode.assessment.dto;

import com.slabcode.assessment.entity.Project;
import com.slabcode.assessment.constant.ProgressStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO {
    private Integer id;
    private String name;
    private String description;
    private Calendar creationDate;
    private Calendar terminationDate;
    private ProgressStatus status;
    //TODO add Set of tasks if needed
}
