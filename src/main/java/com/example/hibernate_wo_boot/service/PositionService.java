package com.example.hibernate_wo_boot.service;

import com.example.hibernate_wo_boot.dao.PositionDao;
import com.example.hibernate_wo_boot.model.mapper.EmployeeMapper;
import com.example.hibernate_wo_boot.model.dto.EmployeeShortDto;
import com.example.hibernate_wo_boot.model.Position;
import com.example.hibernate_wo_boot.model.dto.PositionShortDto;
import com.example.hibernate_wo_boot.model.mapper.PositionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionDao positionDao;

    public void save(Position position) {
        positionDao.persist(position);
    }

    @Transactional(readOnly = true)
    public Position findById(Long positionId) {
        return positionDao.findById(positionId);
    }

    @Transactional
    public void update(Long positionId, Position position) {
        var current = positionDao.findById(positionId);
        current.setName(position.getName());
        positionDao.persist(current);
    }

    public void delete(Position position) {
        positionDao.delete(position);
    }

    @Transactional(readOnly = true)
    public List<PositionShortDto> findAll() {
        return positionDao.findAll().stream().map(PositionMapper::fromModel).toList();
    }

    @Transactional(readOnly = true)
    public List<EmployeeShortDto> getEmployeesForPosition(String name) {
        Position position = positionDao.findByName(name);
        return position.getEmployees().stream().map(EmployeeMapper::fromModel).toList();
    }
}
