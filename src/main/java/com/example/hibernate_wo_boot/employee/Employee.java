package com.example.hibernate_wo_boot.employee;

import com.example.hibernate_wo_boot.position.Position;
import com.example.hibernate_wo_boot.project.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;
    private String firstName;
    private String secondName;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToMany
    @JoinTable(name = "projects_employees",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new LinkedHashSet<>();

}
