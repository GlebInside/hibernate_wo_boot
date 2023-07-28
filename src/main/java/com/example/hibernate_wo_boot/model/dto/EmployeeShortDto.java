package com.example.hibernate_wo_boot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeShortDto {
    private String firstName;
    private String SecondName;
}
