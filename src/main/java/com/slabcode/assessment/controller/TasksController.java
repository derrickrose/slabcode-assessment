package com.slabcode.assessment.controller;

import com.slabcode.assessment.dto.TaskDTO;
import com.slabcode.assessment.entity.Task;
import com.slabcode.assessment.mapper.TaskDTOMapper;
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
    private TaskDTOMapper taskDTOMapper;

    @Autowired
    TasksController(TasksService tasksService, TaskDTOMapper taskDTOMapper) {
        this.tasksService = tasksService;
        this.taskDTOMapper = taskDTOMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TasksController.create}")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskDTOMapper.toTask(taskDTO);
        task = tasksService.save(task);
        taskDTO = taskDTOMapper.fromTask(task);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(taskDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TasksController.get}")
    public TaskDTO getTaskById(@PathVariable Integer id) {
        return taskDTOMapper.fromTask(tasksService.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TasksController.search}")
    public TaskDTO findTaskByName(@RequestParam String name) {
        return taskDTOMapper.fromTask(tasksService.findByName(name));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TasksController.status.update}")
    public ResponseEntity<TaskDTO> updateStatus(@PathVariable Integer id, @RequestParam String status) {
        Task task = tasksService.updateStatus(id, status);
        TaskDTO taskDTO = taskDTOMapper.fromTask(task);
        return ResponseEntity.ok(taskDTO);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TasksController.update}")
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO taskDTO) {
        Task task = taskDTOMapper.toTask(taskDTO);
        task = tasksService.update(task);
        taskDTO = taskDTOMapper.fromTask(task);
        return ResponseEntity.ok(taskDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${TasksController.delete}")
    public void deleteById(@PathVariable Integer id) {
        tasksService.deteleById(id);
    }
}
