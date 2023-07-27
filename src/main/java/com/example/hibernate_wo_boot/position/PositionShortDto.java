package com.example.hibernate_wo_boot.position;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionShortDto {
    private String name;

    public static PositionShortDto fromModel(Position position) {
        return new PositionShortDto(position.getName());
    }

}
