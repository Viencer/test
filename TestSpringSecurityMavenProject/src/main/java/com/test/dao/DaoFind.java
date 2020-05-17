package com.test.dao;

import com.test.model.Personal;

import java.util.List;

public interface DaoFind {
    List<Personal> selectAllPersonal();

    Personal findById(int id);

    Personal getByName(String name);
}
