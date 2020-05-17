package com.test.dao;

import java.sql.SQLException;

public interface DaoChange {
    void update(int id, String lastName, int bossId, int exp, int salary, int jobId) throws SQLException;
    void delete(int id) throws SQLException;
    void create(String firstName, String lastName, int bossId, int exp, int salary, int jobId, String username, String password) throws SQLException;
}
