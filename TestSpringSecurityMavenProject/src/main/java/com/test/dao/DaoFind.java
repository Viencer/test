package com.test.dao;

import com.test.model.Patient;
import com.test.model.Personal;

import java.util.List;

public interface DaoFind {
    List<Personal> selectAllPersonal();

    List<Patient> selectAllPatient();

    List<Personal> findByIdList(int id);

    Personal findById(int id);

    Personal getByName(String name);

    List<Personal> findByLastName(String lastName);
}
