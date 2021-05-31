package com.slabcode.assessment.controller;

import com.slabcode.assessment.dto.TaskDTO;
import com.slabcode.assessment.service.TasksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tasks")
@Api(tags = "tasks")
public class TasksController {

    private TasksService tasksService;

    @Autowired
    TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TasksController.get}")
    public TaskDTO getTaskById(@PathVariable  Integer id) {
        return TaskDTO.fromTask(tasksService.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TasksController.search}")
    public TaskDTO findTaskByName(@RequestParam String name) {
        return TaskDTO.fromTask(tasksService.findByName(name));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TasksController.create}")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        taskDTO = TaskDTO.fromTask(tasksService.save(taskDTO.toTask()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(taskDTO);
    }
}
