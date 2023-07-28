package com.example.hibernate_wo_boot.dao;

import com.example.hibernate_wo_boot.model.Position;

public interface PositionDao extends Dao<Position> {

    Position findByName(String name);

}
