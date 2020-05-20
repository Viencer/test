package com.test.service;

import com.test.dao.DaoConnectionImpl;
import com.test.model.Patient;
import com.test.model.Personal;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicePersonalImpl implements UserServicePersonal, UserServicePatient {
    private static Logger logger = Logger.getLogger(UserServicePersonalImpl.class);

    @Override
    public List<Personal> allPersonal() {
        logger.debug("called allPersonal() method. UserServiceImpl.class");
        return DaoConnectionImpl.getInstance().selectAllPersonal();
    }

    @Override
    public List<Patient> allPatient() {
        return DaoConnectionImpl.getInstance().selectAllPatient();
    }

    @Override
    public void createPersonal(String firstName, String lastName, int bossId, int premium, int salary, int jobId,
                               int department_id, Integer patient_id, String username, String password) {
        logger.debug("called create() method. UserServiceImpl.class");
        DaoConnectionImpl.getInstance().createPersonal(firstName, lastName, bossId, premium, salary, jobId,
                department_id, patient_id);
        DaoConnectionImpl.getInstance().createPersonalData(username, password);
    }

    @Override
    public void updatePersonal(int id, String lastName, int bossId, int premium, int salary, int jobId, int department_id, Integer patient_id) {
        logger.debug("called update() method. UserServiceImpl.class");
        DaoConnectionImpl.getInstance().updatePersonal(id, lastName, bossId, premium, salary, jobId, department_id, patient_id);
    }

    @Override
    public void updatePatient(int id, int diagnosisId, int medicineId) {
        DaoConnectionImpl.getInstance().updatePatient(id, diagnosisId, medicineId);
    }

    @Override
    public Patient getByIdPatient(int id) {
        return DaoConnectionImpl.getInstance().findByIdPatient(id);
    }

    @Override
    public void deletePatient(int id) {
        DaoConnectionImpl.getInstance().deletePatient(id);
    }

    @Override
    public List<Patient> getByIdPatientList(int id) {
        return DaoConnectionImpl.getInstance().getByIdPatientList(id);
    }

    @Override
    public List<Patient> findByLastNamePatient(String lastName) {
       return DaoConnectionImpl.getInstance().findByLastNamePatient(lastName);
    }

    @Override
    public void createPatient(String firstName, String lastName, String position, int phone,
                              String address, int diagnosisId, int medicineId) {
        DaoConnectionImpl.getInstance().createPatient(firstName, lastName, position, phone, address, diagnosisId, medicineId);
    }

    @Override
    public void deletePersonal(int id) {
        logger.debug("called delete() method. UserServiceImpl.class");
        DaoConnectionImpl.getInstance().deletePersonal(id);
    }

    @Override
    public List<Personal> getByIdPersonalList(int id) {
        logger.debug("called getById() method. UserServiceImpl.class");
        return DaoConnectionImpl.getInstance().findByIdList(id);
    }

    @Override
    public Personal getByIdPersonal(int id) {
        logger.debug("called getById() method. UserServiceImpl.class");
        return DaoConnectionImpl.getInstance().findByIdPersonal(id);
    }

    @Override
    public Personal getByUserNamePersonal(String name) {
        logger.debug("called getByName() method. UserServiceImpl.class");
        return DaoConnectionImpl.getInstance().getByName(name);
    }

    @Override
    public List<Personal> findByLastName(String lastName) {
        return DaoConnectionImpl.getInstance().findByLastName(lastName);
    }


}
