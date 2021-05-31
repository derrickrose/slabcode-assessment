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
    private Long id;
    private String name;
    private String description;
    private Calendar creationDate;
    private Calendar terminationDate;
    private ProgressStatus status;


    public static ProjectDTO fromProject(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setCreationDate(project.getCreationDate());
        projectDTO.setTerminationDate(project.getTerminationDate());
        projectDTO.setStatus(project.getStatus());
        return projectDTO;
    }

    public Project toProject() {
        Project project = new Project();
        project.setId(this.id);
        project.setName(this.name);
        project.setDescription(this.description);
        project.setCreationDate(this.creationDate);
        project.setTerminationDate(this.terminationDate);
        project.setStatus(this.status);
        return project;
    }
}
