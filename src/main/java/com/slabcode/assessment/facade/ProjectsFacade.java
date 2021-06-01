package com.slabcode.assessment.facade;

import com.slabcode.assessment.entity.Project;
import com.slabcode.assessment.exception.CustomException;
import com.slabcode.assessment.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

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


    public Project findById(Integer id) {
        return projectsRepository.findById(id).orElse(null);
    }

    public boolean existsById(Integer id) {
        return projectsRepository.existsById(id);
    }

    @Transactional
    public void deleteById(Integer id) {
        projectsRepository.deleteById(id);
    }

    public List<Project> findAll() {//TODO paginate nex time
        return projectsRepository.findAll();
    }

}
