package com.test.dao;


public interface DaoChange {
    void updatePersonal(int id, String lastName, int bossId, int exp, int salary, int jobId,
                        int department_id, Integer patient_id);

    void deletePersonal(int id);

    void createPersonal(String firstName, String lastName, int bossId, int exp, int salary, int jobId,
                        int department_id, Integer patient_id);

    void createPersonalData(String username, String password);
}
