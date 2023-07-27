package com.example.hibernate_wo_boot.employee;

import com.example.hibernate_wo_boot.project.ProjectShortDto;

import java.util.List;

public class EmployeeDto {
    public final String secondName;
    public final String firstName;
    public final String positionName;
    public final List<ProjectShortDto> projects;

    public EmployeeDto(Employee employee) {
        firstName = employee.getFirstName();
        secondName = employee.getSecondName();
        positionName = employee.getPosition().getName();
        projects = employee.getProjects().stream().map(ProjectShortDto::fromModel).toList();
    }
}
