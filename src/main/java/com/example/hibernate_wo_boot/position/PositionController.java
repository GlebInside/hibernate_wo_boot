package com.example.hibernate_wo_boot.position;

import com.example.hibernate_wo_boot.employee.EmployeeShortDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionDaoImpl positionDao;

    @PostMapping("")
    public void addPosition(@RequestBody Position position) {
        positionDao.save(position);
    }

    @GetMapping("/{positionId}")
    public PositionShortDto getById(@PathVariable Long positionId) {
        return PositionShortDto.fromModel(positionDao.findById(positionId));
    }

    @GetMapping()
    public List<PositionShortDto> findAll() {
        return positionDao.findAll().stream().map(PositionShortDto::fromModel).toList();
    }

    @Transactional
    @PutMapping("/{positionId}")
    public void updatePosition(@PathVariable Long positionId, @RequestBody Position position) {
        Position p = positionDao.findById(positionId);
        p.setName(position.getName());
        positionDao.save(p);
    }

    @DeleteMapping("/{positionId}")
    public void deletePosition(@PathVariable Long positionId) {
        positionDao.delete(positionDao.getByKey(positionId));
    }

    @Transactional
    @GetMapping("/{name}/employees")
    public List<EmployeeShortDto> getEmployeesForPosition(@PathVariable String name) {
        Position position = positionDao.findByName(name);
        return position.getEmployees().stream().map(EmployeeShortDto::fromModel).toList();
    }
}
