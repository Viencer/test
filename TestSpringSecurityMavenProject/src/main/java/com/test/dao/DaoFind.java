package com.test.dao;

import com.test.model.Personal;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface DaoFind {
    List<Personal> selectAllPersonal() throws SQLException, NamingException;

    Personal findById(int id) throws SQLException;

    Personal getByName(String name) throws SQLException;
}
