package com.slabcode.assessment.controller;

import com.slabcode.assessment.dto.ProjectDTO;
import com.slabcode.assessment.service.ProjectsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/projects")
@Api(tags = "projects")
public class ProjectsController {

    private ProjectsService ProjectsService;

    @Autowired
    ProjectsController(ProjectsService ProjectsService) {
        this.ProjectsService = ProjectsService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ProjectsController.get}")
    public ProjectDTO getProjectById(@PathVariable Long id) {
        return ProjectDTO.fromProject(ProjectsService.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ProjectsController.search}")
    public ProjectDTO findProjectByName(@RequestParam String name) {
        return ProjectDTO.fromProject(ProjectsService.findByName(name));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${ProjectsController.create}")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO ProjectDTO) {
        ProjectDTO = ProjectDTO.fromProject(ProjectsService.save(ProjectDTO.toProject()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ProjectDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(ProjectDTO);
    }
}
