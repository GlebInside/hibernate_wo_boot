package com.example.hibernate_wo_boot.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeShortDto {
    private String firstName;
    private String SecondName;

    public static EmployeeShortDto fromModel(Employee employee) {
        return new EmployeeShortDto(employee.getFirstName(), employee.getSecondName());
    }
}
