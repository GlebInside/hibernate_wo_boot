package com.example.hibernate_wo_boot.model.mapper;

import com.example.hibernate_wo_boot.model.Employee;
import com.example.hibernate_wo_boot.model.dto.EmployeeShortDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeMapper {

    public static EmployeeShortDto fromModel(Employee employee) {
        return new EmployeeShortDto(employee.getFirstName(), employee.getSecondName());
    }
}
