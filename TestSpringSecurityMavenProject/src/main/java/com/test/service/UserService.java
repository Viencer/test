package com.test.service;

import com.test.model.Personal;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

@Service
public interface UserService {

    List<Personal> allPersonal() throws SQLException, NamingException;

    void create(String firstName, String lastName, int bossId, int exp, int salary, int jobId, String username, String password) throws SQLException;

    void update(int id, String lastName, int bossId, int exp, int salary, int jobId) throws SQLException;

    void delete(int id) throws SQLException;

    Personal getById(int id) throws SQLException;

    Personal getByName(String name) throws SQLException;
}
