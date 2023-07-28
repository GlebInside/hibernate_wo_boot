package com.example.hibernate_wo_boot.model.mapper;

import com.example.hibernate_wo_boot.model.Project;
import com.example.hibernate_wo_boot.model.dto.ProjectShortDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectMapper {

    public static ProjectShortDto fromModel(Project project) {
        return ProjectShortDto.builder().name(project.getName()).build();
    }
}
