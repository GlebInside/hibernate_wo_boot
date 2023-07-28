package com.example.hibernate_wo_boot.controller;


import com.example.hibernate_wo_boot.model.dto.EmployeeDto;
import com.example.hibernate_wo_boot.model.Project;
import com.example.hibernate_wo_boot.model.mapper.ProjectMapper;
import com.example.hibernate_wo_boot.service.ProjectService;
import com.example.hibernate_wo_boot.model.dto.ProjectShortDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectShortDto> addProject(@RequestBody Project project) {
        projectService.save(project);
        return ResponseEntity.ok(ProjectMapper.fromModel(project));
    }

    @GetMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectShortDto> getById(@PathVariable Long projectId) {
        return ResponseEntity.ok(ProjectMapper.fromModel(projectService.findById(projectId)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectShortDto>> findAll() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @PutMapping(value = "/{projectId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectShortDto> updateProject(@PathVariable Long projectId, @RequestBody Project project) {
        projectService.update(projectId, project);
        return ResponseEntity.ok(ProjectMapper.fromModel(projectService.findById(projectId)));
    }

    @DeleteMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteProject(@PathVariable Long projectId) {
        projectService.delete(projectService.findById(projectId));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{projectId}/addEmployee/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addEmployee(@PathVariable Long projectId, @PathVariable Long employeeId) {
        projectService.addEmployee(projectId, employeeId);
    }

    @GetMapping(value = "/{name}/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDto>> getEmployees(@PathVariable("name") String name) {
        var employees = projectService.getEmployees(name);
        return ResponseEntity.ok(employees);
    }
}
