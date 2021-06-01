package com.slabcode.assessment.service;

import com.slabcode.assessment.constant.ProgressStatus;
import com.slabcode.assessment.entity.Project;
import com.slabcode.assessment.exception.CustomException;
import com.slabcode.assessment.facade.ProjectsFacade;
import com.slabcode.assessment.facade.TasksFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class ProjectsService {

    private ProjectsFacade projectsFacade;

    private TasksFacade tasksFacade;

    @Autowired
    public ProjectsService(ProjectsFacade projectsFacade, TasksFacade tasksFacade) {
        this.projectsFacade = projectsFacade;
        this.tasksFacade = tasksFacade;
    }

    public Project findByName(String name) {
        return projectsFacade.findByName(name);
    }

    public Project create(Project project) {
        if (!isCreationDateValid(project)) {
            throw new CustomException("Project creationDate shouldn't be in the past", HttpStatus.BAD_REQUEST);
        }
        if (project.getCreationDate() == null) {
            project.setCreationDate(Calendar.getInstance());
        }
        if (!isTerminationDateValid(project)) {
            throw new CustomException("Project terminationDate not valid", HttpStatus.BAD_REQUEST);
        }
        return projectsFacade.save(project);
    }

    public Project update(Project project) {
        if (projectsFacade.existsById(project.getId())) {
            Project existingProject = projectsFacade.findById(project.getId());
            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());
            if ((!existingProject.getTerminationDate().equals(project.getTerminationDate()))
                    && isTerminationDateValid(project)) {
                existingProject.setTerminationDate(project.getTerminationDate());
            }
            return projectsFacade.save(existingProject);
        } else {
            throw new CustomException("Project does not exist", HttpStatus.NOT_FOUND);
        }
    }

    public Project updateStatus(Integer id, String status) {
        if (!projectsFacade.existsById(id)) {
            throw new CustomException("Project does not exist", HttpStatus.NOT_FOUND);
        }
        if (ProgressStatus.isValid(status)) {
            Project project = projectsFacade.findById(id);
            project.setStatus(ProgressStatus.fromRaw(status));
            if (isMovingToDone(project) && tasksFacade.getNotDoneTasksByProjectId(id).size() > 0) {
                throw new CustomException("Operation not allowed since there are some tasks not done yet", HttpStatus.BAD_REQUEST);
            }
            return projectsFacade.save(project);
        } else {
            throw new CustomException("Invalid status " + status, HttpStatus.BAD_REQUEST);
        }
    }

    public Project findById(Integer id) {
        return projectsFacade.findById(id);
    }

    public void deteleById(Integer id) {
        if (projectsFacade.existsById(id)) {
            projectsFacade.deleteById(id);
        } else {
            throw new CustomException("Project doest not exist", HttpStatus.NOT_FOUND);
        }
    }

    private boolean isCreationDateValid(Project project) {
        return project.getCreationDate() == null ||
                (project.getCreationDate() != null && project.getCreationDate().after(Calendar.getInstance()));
    }

    private boolean isTerminationDateValid(Project project) {
        return project.getTerminationDate() == null ||
                (project.getTerminationDate() != null && project.getTerminationDate().after(project.getCreationDate()));
    }

    private boolean isMovingToDone(Project project) {
        return ProgressStatus.DONE.equals(project.getStatus());
    }
}
