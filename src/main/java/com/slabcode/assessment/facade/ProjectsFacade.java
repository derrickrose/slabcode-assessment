package com.slabcode.assessment.facade;

import com.slabcode.assessment.entity.Project;
import com.slabcode.assessment.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ProjectsFacade {
    private ProjectsRepository projectsRepository;

    @Autowired
    ProjectsFacade(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    public Project findByName(String name) {
        return projectsRepository.findByName(name);
    }

    @Transactional
    public Project save(Project project) {
        return projectsRepository.save(project);
    }


    public Project findById(Long id) {
        return projectsRepository.findById(id);
    }

}
