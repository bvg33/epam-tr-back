package com.epam.tr.dao;

import java.util.List;

public interface Dao<T> {
    void insert(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> getAll();
    T getById(int id);
}
