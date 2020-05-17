package com.test.service;

import com.test.dao.DaoConnectionImpl;
import com.test.model.Personal;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<Personal> allPersonal() throws SQLException {
        List<Personal> personal = DaoConnectionImpl.getInstance().selectAllPersonal();
        System.out.println(personal);
        return personal;
    }

    @Override
    public void create(String firstName, String lastName, int bossId, int exp, int salary, int jobId, String username, String password) throws SQLException {
        DaoConnectionImpl.getInstance().create(firstName, lastName, bossId, exp, salary, jobId, username, password);
    }

    @Override
    public void update(int id, String lastName, int bossId, int exp, int salary, int jobId) throws SQLException {
        DaoConnectionImpl.getInstance().update(id, lastName, bossId, exp, salary, jobId);
    }

    @Override
    public void delete(int id) throws SQLException {
        DaoConnectionImpl.getInstance().delete(id);
    }

    @Override
    public Personal getById(int id) throws SQLException {
        return DaoConnectionImpl.getInstance().findById(id);
    }

    @Override
    public Personal getByName(String name) throws SQLException {
        return DaoConnectionImpl.getInstance().getByName(name);
    }
}
