package com.test.dao;

import com.test.model.Patient;
import com.test.model.Personal;

import java.util.List;

public interface DaoFind {
    List<Personal> selectAllPersonal();

    List<Patient> selectAllPatient();

    List<Personal> findByIdList(int id);

    Personal findByIdPersonal(int id);

    Patient findByIdPatient(int id);

    Personal getByName(String name);

    List<Personal> findByLastName(String lastName);

    List<Patient> getByIdPatientList(int id);

    List<Patient> findByLastNamePatient(String lastName);
}
