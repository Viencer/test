package com.test.service;

import com.test.dao.DaoChange;
import com.test.dao.DaoFind;
import com.test.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicePersonalImpl implements UserServicePersonal {
    private static Logger logger = Logger.getLogger(UserServicePersonalImpl.class);


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
    public void createPersonal(String firstName, String lastName, int bossId, int premium, int salary, int jobId,
                               int department_id, Integer patient_id, String username, String password) {
        logger.debug("called createPersonal() method. UserServiceImpl.class");
        daoChange.createPersonal(firstName, lastName, bossId, premium, salary, jobId,
                department_id, patient_id);
        daoChange.createPersonalData(username, password, jobId);
    }

    @Override
    public void updatePersonal(int id, String lastName, int bossId, int premium, int salary, int jobId, int department_id, Integer patient_id) {
        logger.debug("called updatePersonal() method. UserServiceImpl.class");
        daoChange.updatePersonal(id, lastName, bossId, premium, salary, jobId, department_id, patient_id);
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
}
