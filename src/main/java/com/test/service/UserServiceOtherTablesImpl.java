package com.test.service;

import com.test.dao.DaoFind;
import com.test.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceOtherTablesImpl implements UserServiceOtherTables {
    private static Logger logger = Logger.getLogger(UserServiceOtherTablesImpl.class);

    private DaoFind daoFind;

    @Autowired
    public void setDaoFind(DaoFind daoFind) {
        this.daoFind = daoFind;
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
