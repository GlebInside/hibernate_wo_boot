package com.example.hibernate_wo_boot.dao;

import com.example.hibernate_wo_boot.model.Project;

public interface ProjectDao extends Dao<Project> {

    Project findByName(String name);

}
