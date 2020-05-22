package com.test.dao;


public interface DaoChange {
    void updatePersonal(int id, String lastName, int bossId, int exp, int salary, int jobId,
                        int department_id, Integer patient_id);

    void updatePatient(int id, int diagnosisId, int medicineId);

    void deletePersonal(int id);

    void deletePatient(int id);

    void createPersonal(String firstName, String lastName, int bossId, int exp, int salary, int jobId,
                        int department_id, Integer patient_id);

    void createPatient(String firstName, String lastName, String position, int phone,
                       String address, int diagnosisId, int medicineId);

    void createPersonalData(String username, String password, int jobId);
}
