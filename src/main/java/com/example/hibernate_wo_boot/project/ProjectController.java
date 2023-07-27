package com.example.hibernate_wo_boot.project;


import com.example.hibernate_wo_boot.employee.Employee;
import com.example.hibernate_wo_boot.employee.EmployeeDaoImpl;
import com.example.hibernate_wo_boot.employee.EmployeeDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectDaoImpl projectDao;
    private final EmployeeDaoImpl employeeDao;

    @PostMapping("")
    public void addProject(@RequestBody Project project) {
        projectDao.save(project);
    }

    @GetMapping("/{projectId}")
    public ProjectShortDto getById(@PathVariable Long projectId) {
        return ProjectShortDto.fromModel(projectDao.findById(projectId));
    }

    @GetMapping()
    public List<ProjectShortDto> findAll() {
        return projectDao.findAll().stream().map(ProjectShortDto::fromModel).toList();
    }

    @Transactional
    @PutMapping("/{projectId}")
    public void updateProject(@PathVariable Long projectId, @RequestBody Project project) {
        Project p = projectDao.findById(projectId);
        p.setName(project.getName());
        projectDao.save(p);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectDao.delete(projectDao.findById(projectId));
    }

    @Transactional
    @PutMapping("/{projectId}/addEmployee/{employeeId}")
    public void addEmployee(@PathVariable Long projectId, @PathVariable Long employeeId) {
        Project p = projectDao.findById(projectId);
        Employee e = employeeDao.findById(employeeId);
        p.getEmployees().add(e);
        projectDao.save(p);
    }

    @Transactional
    @GetMapping("/{name}/employees")
    public List<EmployeeDto> getEmployees(@PathVariable("name") String name) {
        var project = projectDao.findByName(name);
        return project.getEmployees().stream().map(EmployeeDto::new).toList();
    }
}
