package com.test.service;

import com.test.model.Personal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<Personal> allPersonal();

    void createPersonal(String firstName, String lastName, int bossId, int exp, int salary, int jobId, String username, String password);

    void updatePersonal(int id, String lastName, int bossId, int exp, int salary, int jobId);

    void deletePersonal(int id);

    Personal getByIdPersonal(int id);

    Personal getByUserNamePersonal(String name);
}
