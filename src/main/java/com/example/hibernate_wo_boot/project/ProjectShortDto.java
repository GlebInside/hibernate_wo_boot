package com.example.hibernate_wo_boot.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectShortDto {

    private String name;

    public static ProjectShortDto fromModel(Project project) {
        return ProjectShortDto.builder().name(project.getName()).build();
    }
}
