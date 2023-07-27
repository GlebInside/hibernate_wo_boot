package com.example.hibernate_wo_boot.employee;

import com.example.hibernate_wo_boot.position.Position;
import com.example.hibernate_wo_boot.position.PositionDaoImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeDaoImpl employeeDao;
    private final PositionDaoImpl positionDao;

    @PostMapping("")
    public void addEmployee(@RequestBody Employee employee) {
        employeeDao.save(employee);
    }

    @Transactional
    @PutMapping("/{employeeId}")
    public void updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
        Employee e = employeeDao.findById(employeeId);
        e.setFirstName(employee.getFirstName());
        e.setSecondName(employee.getSecondName());
        employeeDao.save(e);
    }

    @Transactional
    @GetMapping("/{employeeId}")
    public EmployeeDto getById(@PathVariable Long employeeId) {
        return new EmployeeDto(employeeDao.findById(employeeId));
    }

    @Transactional
    @GetMapping()
    public List<EmployeeDto> findAll() {
        return employeeDao.findAll().stream().map(EmployeeDto::new).toList();
    }

    @DeleteMapping("{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeDao.delete(employeeDao.findById(employeeId));
    }

    @Transactional
    @PutMapping("/{employeeId}/updatePosition/{positionId}")
    public void employeeUpdatePosition(@PathVariable Long employeeId, @PathVariable Long positionId) {
        Employee e = employeeDao.findById(employeeId);
        Position p = positionDao.findById(positionId);
        e.setPosition(p);
        employeeDao.save(e);
    }
}
