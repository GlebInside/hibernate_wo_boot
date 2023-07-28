package com.example.hibernate_wo_boot.dao;

import java.util.List;

public interface Dao<T> {
    T findById(Long id);

    List<T> findAll();

    void persist(T e);

    void merge(Long id, T e);

    void delete(T e);
}
