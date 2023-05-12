package com.company.dao;

import jakarta.ejb.Local;

import java.io.Serializable;
import java.util.List;

@Local
public interface Dao<T, ID extends Serializable> {
    List<T> getAll();
}