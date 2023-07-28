package com.example.hibernate_wo_boot.service;

import com.example.hibernate_wo_boot.dao.Dao;
import com.example.hibernate_wo_boot.model.dto.EmployeeDto;
import com.example.hibernate_wo_boot.model.Employee;
import com.example.hibernate_wo_boot.model.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final Dao<Employee> employeeDao;
    private final Dao<Position> positionDao;

    public void save(Employee employee) {
        employeeDao.persist(employee);
    }

    @Transactional(readOnly = true)
    public EmployeeDto findById(Long employeeId) {
        return new EmployeeDto(employeeDao.findById(employeeId));
    }

    @Transactional
    public void update(Long employeeId, Employee employee) {
        var current = employeeDao.findById(employeeId);
        if (employee.getFirstName() != null) {
            current.setFirstName(employee.getFirstName());
        }
        if (employee.getSecondName() != null) {
            current.setSecondName(employee.getSecondName());
        }
        employeeDao.persist(current);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDto> findAll() {
        return employeeDao.findAll().stream().map(EmployeeDto::new).toList();
    }

    public void delete(Long employeeId) {
        var employee = employeeDao.findById(employeeId);
        employeeDao.delete(employee);
    }

    @Transactional
    public void updatePosition(Long employeeId, Long positionId) {
        Employee e = employeeDao.findById(employeeId);
        Position p = positionDao.findById(positionId);
        e.setPosition(p);
        save(e);
    }
}
