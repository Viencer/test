package com.test.service;

import com.test.dao.DaoChange;
import com.test.dao.DaoFind;
import com.test.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServicePersonal, UserServicePatient, UserServiceOtherTables {
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);


    private DaoFind daoFind;

    @Autowired
    public void setDaoFind(DaoFind daoFind) {
        this.daoFind = daoFind;
    }

    private DaoChange daoChange;

    @Autowired
    public void setDaoChange(DaoChange daoChange) {
        this.daoChange = daoChange;
    }

    @Override
    public List<Personal> allPersonal() {
        logger.debug("called allPersonal() method. UserServiceImpl.class");
        return daoFind.selectAllPersonal();
    }

    @Override
    public List<Patient> allPatient() {
        logger.debug("called allPatient() method. UserServiceImpl.class");
        return daoFind.selectAllPatient();
    }

    @Override
    public void createPersonal(String firstName, String lastName, int bossId, int premium, int salary, int jobId,
                               int department_id, Integer patient_id, String username, String password) {
        logger.debug("called createPersonal() method. UserServiceImpl.class");
        daoChange.createPersonal(firstName, lastName, bossId, premium, salary, jobId,
                department_id, patient_id);
        daoChange.createPersonalData(username, password);
    }

    @Override
    public void updatePersonal(int id, String lastName, int bossId, int premium, int salary, int jobId, int department_id, Integer patient_id) {
        logger.debug("called updatePersonal() method. UserServiceImpl.class");
        daoChange.updatePersonal(id, lastName, bossId, premium, salary, jobId, department_id, patient_id);
    }

    @Override
    public void updatePatient(int id, int diagnosisId, int medicineId) {
        logger.debug("called updatePatient() method. UserServiceImpl.class");
        daoChange.updatePatient(id, diagnosisId, medicineId);
    }

    @Override
    public Patient getByIdPatient(int id) {
        logger.debug("called getByIdPatient() method. UserServiceImpl.class");
        return daoFind.findByIdPatient(id);
    }

    @Override
    public void deletePatient(int id) {
        logger.debug("called deletePatient() method. UserServiceImpl.class");
        daoChange.deletePatient(id);
    }

    @Override
    public List<Patient> getByIdPatientList(int id) {
        logger.debug("called getByIdPatientList() method. UserServiceImpl.class");
        return daoFind.getByIdPatientList(id);
    }

    @Override
    public List<Patient> findByLastNamePatient(String lastName) {
        logger.debug("called findByLastNamePatient() method. UserServiceImpl.class");
        return daoFind.findByLastNamePatient(lastName);
    }

    @Override
    public void createPatient(String firstName, String lastName, String position, int phone,
                              String address, int diagnosisId, int medicineId) {
        logger.debug("called createPatient() method. UserServiceImpl.class");
        daoChange.createPatient(firstName, lastName, position, phone, address, diagnosisId, medicineId);
    }

    @Override
    public void deletePersonal(int id) {
        logger.debug("called deletePersonal() method. UserServiceImpl.class");
        daoChange.deletePersonal(id);
    }

    @Override
    public List<Personal> getByIdPersonalList(int id) {
        logger.debug("called getByIdPersonalList() method. UserServiceImpl.class");
        return daoFind.findByIdList(id);
    }

    @Override
    public Personal getByIdPersonal(int id) {
        logger.debug("called getByIdPersonal() method. UserServiceImpl.class");
        return daoFind.findByIdPersonal(id);
    }

    @Override
    public Personal getByUserNamePersonal(String name) {
        logger.debug("called getByUserNamePersonal() method. UserServiceImpl.class");
        return daoFind.getByName(name);
    }

    @Override
    public List<Personal> findByLastName(String lastName) {
        logger.debug("called findByLastName() method. UserServiceImpl.class");
        return daoFind.findByLastName(lastName);
    }


    @Override
    public List<Department> getAllDepartments() {
        logger.debug("called getAllDepartments() method. UserServiceImpl.class");
        return daoFind.getAllDepartments();
    }

    @Override
    public List<Diagnosis> getAllDiagnosis() {
        logger.debug("called getAllDiagnosis() method. UserServiceImpl.class");
        return daoFind.getAllDiagnosis();
    }

    @Override
    public List<Jobs> getAllJobs() {
        logger.debug("called getAllJobs() method. UserServiceImpl.class");
        return daoFind.getAllJobs();
    }

    @Override
    public List<Medicine> getAllMedicines() {
        logger.debug("called getAllMedicines() method. UserServiceImpl.class");
        return daoFind.getAllMedicines();
    }

    @Override
    public List<Treatment> getAllTreatments() {
        logger.debug("called getAllTreatments() method. UserServiceImpl.class");
        return daoFind.getAllTreatments();
    }
}
