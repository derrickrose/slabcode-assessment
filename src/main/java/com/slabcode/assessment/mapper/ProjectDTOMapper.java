package com.slabcode.assessment.mapper;

import com.slabcode.assessment.dto.ProjectDTO;
import com.slabcode.assessment.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectDTOMapper {

    public ProjectDTO fromProject(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setCreationDate(project.getCreationDate());
        projectDTO.setTerminationDate(project.getTerminationDate());
        projectDTO.setStatus(project.getStatus());
        return projectDTO;
    }

    public Project toProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setCreationDate(projectDTO.getCreationDate());
        project.setTerminationDate(projectDTO.getTerminationDate());
        project.setStatus(projectDTO.getStatus());
        return project;
    }
}
