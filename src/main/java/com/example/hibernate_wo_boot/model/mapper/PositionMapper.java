package com.example.hibernate_wo_boot.model.mapper;

import com.example.hibernate_wo_boot.model.Position;
import com.example.hibernate_wo_boot.model.dto.PositionShortDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class PositionMapper {
    public static PositionShortDto fromModel(Position position) {
        return new PositionShortDto(position.getName());
    }
}
