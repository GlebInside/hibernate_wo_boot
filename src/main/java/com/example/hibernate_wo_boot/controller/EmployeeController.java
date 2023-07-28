package com.example.hibernate_wo_boot.controller;

import com.example.hibernate_wo_boot.model.mapper.EmployeeMapper;
import com.example.hibernate_wo_boot.model.dto.EmployeeDto;
import com.example.hibernate_wo_boot.service.EmployeeService;
import com.example.hibernate_wo_boot.model.dto.EmployeeShortDto;
import com.example.hibernate_wo_boot.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeShortDto> addEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return ResponseEntity.ok(EmployeeMapper.fromModel(employee));
    }

    @PutMapping(value = "/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
        employeeService.update(employeeId, employee);
        return ResponseEntity.ok(employeeService.findById(employeeId));
    }

    @GetMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.findById(employeeId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable Long employeeId) {
        employeeService.delete(employeeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{employeeId}/updatePosition/{positionId}")
    public ResponseEntity<EmployeeDto> employeeUpdatePosition(@PathVariable Long employeeId, @PathVariable Long positionId) {
        employeeService.updatePosition(employeeId, positionId);
        return ResponseEntity.ok(employeeService.findById(employeeId));
    }
}
