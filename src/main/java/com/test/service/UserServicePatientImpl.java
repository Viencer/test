package com.test.service;

import com.test.dao.DaoChange;
import com.test.dao.DaoFind;
import com.test.model.Patient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicePatientImpl implements UserServicePatient {

    private static Logger logger = Logger.getLogger(UserServicePatientImpl.class);

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
    public List<Patient> allPatient() {
        logger.debug("called allPatient() method. UserServiceImpl.class");
        return daoFind.selectAllPatient();
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
}
