package com.example.hibernate_wo_boot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "project_id", nullable = false)
    private Long projectId;
    private String name;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectId.equals(project.projectId) && name.equals(project.name) && employees.equals(project.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, name, employees);
    }
}
