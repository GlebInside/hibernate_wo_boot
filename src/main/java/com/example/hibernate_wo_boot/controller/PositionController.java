package com.example.hibernate_wo_boot.controller;

import com.example.hibernate_wo_boot.model.dto.EmployeeShortDto;
import com.example.hibernate_wo_boot.model.Position;
import com.example.hibernate_wo_boot.model.mapper.PositionMapper;
import com.example.hibernate_wo_boot.service.PositionService;
import com.example.hibernate_wo_boot.model.dto.PositionShortDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PositionShortDto> addPosition(@RequestBody Position position) {
        positionService.save(position);
        return ResponseEntity.ok(PositionMapper.fromModel(position));
    }

    @GetMapping(value = "/{positionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PositionShortDto> getById(@PathVariable Long positionId) {
        return ResponseEntity.ok(PositionMapper.fromModel(positionService.findById(positionId)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PositionShortDto>> findAll() {
        return ResponseEntity.ok(positionService.findAll());
    }

    @PutMapping(value = "/{positionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PositionShortDto> updatePosition(@PathVariable Long positionId, @RequestBody Position position) {
        positionService.update(positionId, position);
        return ResponseEntity.ok(PositionMapper.fromModel(positionService.findById(positionId)));
    }

    @DeleteMapping(value = "/{positionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePosition(@PathVariable Long positionId) {
        positionService.delete(positionService.findById(positionId));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{name}/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeShortDto>> getEmployeesForPosition(@PathVariable String name) {
        var employees = positionService.getEmployeesForPosition(name);
        return ResponseEntity.ok(employees);
    }
}
