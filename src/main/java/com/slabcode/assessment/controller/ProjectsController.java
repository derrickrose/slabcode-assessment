package com.slabcode.assessment.controller;

import com.slabcode.assessment.dto.ProjectDTO;
import com.slabcode.assessment.dto.TaskDTO;
import com.slabcode.assessment.entity.Project;
import com.slabcode.assessment.entity.Task;
import com.slabcode.assessment.mapper.ProjectDTOMapper;
import com.slabcode.assessment.mapper.TaskDTOMapper;
import com.slabcode.assessment.service.ProjectsService;
import com.slabcode.assessment.service.TasksService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
@Api(tags = "projects")
public class ProjectsController {

    private ProjectsService projectsService;

    private ProjectDTOMapper projectDTOMapper;

    private TasksService tasksService;

    private TaskDTOMapper taskDTOMapper;

    @Autowired
    ProjectsController(ProjectsService ProjectsService, ProjectDTOMapper projectDTOMapper,
                       TasksService tasksService, TaskDTOMapper taskDTOMapper) {
        this.projectsService = ProjectsService;
        this.projectDTOMapper = projectDTOMapper;
        this.tasksService = tasksService;
        this.taskDTOMapper = taskDTOMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ProjectsController.create}")
    public ResponseEntity<ProjectDTO> create(@RequestBody ProjectDTO projectDTO) {
        Project project = projectDTOMapper.toProject(projectDTO);
        projectDTO = projectDTOMapper.fromProject(projectsService.create(project));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(projectDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(projectDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ProjectsController.get}")
    public ProjectDTO getProjectById(@PathVariable Integer id) {
        return projectDTOMapper.fromProject(projectsService.findById(id));
    }

    @GetMapping("/{id}/tasks")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ProjectsController.task.notdone}")
    public Set<TaskDTO> getNotDoneTasksById(@PathVariable Integer id) {
        Set<Task> tasks = tasksService.getNotDoneTasksByProjectId(id);
        Set<TaskDTO> taskDTOS = tasks.stream().map(
                task -> taskDTOMapper.fromTask(task)
        ).collect(Collectors.toSet());
        return taskDTOS;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ProjectsController.search}")
    public ProjectDTO findProjectByName(@RequestParam String name) {
        return projectDTOMapper.fromProject(projectsService.findByName(name));
    }


    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ProjectsController.update}")
    public ResponseEntity<ProjectDTO> update(@RequestBody ProjectDTO projectDTO) {
        Project project = projectDTOMapper.toProject(projectDTO);
        project = projectsService.update(project);
        projectDTO = projectDTOMapper.fromProject(project);
        return ResponseEntity.ok(projectDTO);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ProjectsController.status.update}")
    public ResponseEntity<ProjectDTO> updateStatus(@PathVariable Integer id, @RequestParam String status) {
        Project project = projectsService.updateStatus(id, status);
        ProjectDTO projectDTO = projectDTOMapper.fromProject(project);
        return ResponseEntity.ok(projectDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ProjectsController.delete}")
    public void deleteById(@PathVariable Integer id) {
        projectsService.deteleById(id);
    }

}
