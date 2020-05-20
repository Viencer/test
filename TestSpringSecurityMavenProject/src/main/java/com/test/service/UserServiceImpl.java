package com.test.service;

import com.test.dao.DaoConnectionImpl;
import com.test.model.Personal;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public List<Personal> allPersonal() {
        logger.debug("called allPersonal() method. UserServiceImpl.class");
        return DaoConnectionImpl.getInstance().selectAllPersonal();
    }

    @Override
    public void createPersonal(String firstName, String lastName, int bossId, int com, int salary, int jobId, String username,
                               String password, int department_id, Integer patient_id) {
        logger.debug("called create() method. UserServiceImpl.class");
        DaoConnectionImpl.getInstance().create(firstName, lastName, bossId, com, salary, jobId, username, password,
                department_id, patient_id);
    }

    @Override
    public void updatePersonal(int id, String lastName, int bossId, int com, int salary, int jobId, int department_id, Integer patient_id) {
        logger.debug("called update() method. UserServiceImpl.class");
        DaoConnectionImpl.getInstance().update(id, lastName, bossId, com, salary, jobId, department_id, patient_id);
    }

    @Override
    public void deletePersonal(int id) {
        logger.debug("called delete() method. UserServiceImpl.class");
        DaoConnectionImpl.getInstance().delete(id);
    }

    @Override
    public Personal getByIdPersonal(int id) {
        logger.debug("called getById() method. UserServiceImpl.class");
        return DaoConnectionImpl.getInstance().findById(id);
    }

    @Override
    public Personal getByUserNamePersonal(String name) {
        logger.debug("called getByName() method. UserServiceImpl.class");
        return DaoConnectionImpl.getInstance().getByName(name);
    }

    @Override
    public void dataCreate() {
        DaoConnectionImpl.getInstance().dataCreate();
    }
}
