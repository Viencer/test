package com.test.dao;

import com.test.model.*;

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

    List<Department> getAllDepartments();

    List<Diagnosis> getAllDiagnosis();

    List<Jobs> getAllJobs();

    List<Medicine> getAllMedicines();

    List<Treatment> getAllTreatments();
}
