package com.test.service;

import com.test.model.Personal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<Personal> allPersonal();

    void createPersonal(String firstName, String lastName, int bossId, int com, int salary,
                        int jobId, String username, String password, int department_id, Integer patient_id);

    void updatePersonal(int id, String lastName, int bossId, int com, int salary, int jobId, int department_id, Integer patient_id);

    void deletePersonal(int id);

    Personal getByIdPersonal(int id);

    Personal getByUserNamePersonal(String name);

    void dataCreate();
}
