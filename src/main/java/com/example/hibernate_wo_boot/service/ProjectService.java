package com.example.hibernate_wo_boot.service;

import com.example.hibernate_wo_boot.dao.Dao;
import com.example.hibernate_wo_boot.dao.ProjectDao;
import com.example.hibernate_wo_boot.model.Employee;
import com.example.hibernate_wo_boot.model.dto.EmployeeDto;
import com.example.hibernate_wo_boot.model.Project;
import com.example.hibernate_wo_boot.model.dto.ProjectShortDto;
import com.example.hibernate_wo_boot.model.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final Dao<Employee> employeeDao;

    private final ProjectDao projectDao;

    public void save(Project project) {
        projectDao.persist(project);
    }

    @Transactional(readOnly = true)
    public Project findById(Long projectId) {
        return projectDao.findById(projectId);
    }

    @Transactional
    public void update(Long projectId, Project project) {
        var current = projectDao.findById(projectId);
        current.setName(project.getName());
        projectDao.persist(current);
    }

    public void delete(Project project) {
        projectDao.delete(project);
    }

    @Transactional(readOnly = true)
    public List<ProjectShortDto> findAll() {
        return projectDao.findAll().stream().map(ProjectMapper::fromModel).toList();
    }

    @Transactional
    public void addEmployee(Long projectId, Long employeeId) {
        Project p = findById(projectId);
        Employee e = employeeDao.findById(employeeId);
        p.getEmployees().add(e);
        projectDao.persist(p);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDto> getEmployees(String name) {
        var project = projectDao.findByName(name);
        return project.getEmployees().stream().map(EmployeeDto::new).toList();
    }
}
