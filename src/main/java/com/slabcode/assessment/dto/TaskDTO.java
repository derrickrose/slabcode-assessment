package com.slabcode.assessment.dto;

import com.slabcode.assessment.constant.ProgressStatus;
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
    private Calendar executionDate;
    private ProgressStatus status;
    private Integer projectId;
}
