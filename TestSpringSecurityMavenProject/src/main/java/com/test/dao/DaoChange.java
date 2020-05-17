package com.test.dao;



public interface DaoChange {
    void update(int id, String lastName, int bossId, int exp, int salary, int jobId);

    void delete(int id);

    void create(String firstName, String lastName, int bossId, int exp, int salary, int jobId, String username, String password);
}
