package com.slabcode.assessment.service;

import com.slabcode.assessment.entity.Project;
import com.slabcode.assessment.facade.ProjectsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectsService {

    private ProjectsFacade projectsFacade;

    @Autowired
    public ProjectsService(ProjectsFacade projectsFacade) {
        this.projectsFacade = projectsFacade;
    }

    public Project findByName(String name) {
        return projectsFacade.findByName(name);
    }

    public Project save(Project project) {
        return projectsFacade.save(project);
    }

    public Project findById( Integer id) {
        return projectsFacade.findById(id);
    }
}
