package com.test.service;

import com.test.model.Personal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<Personal> allPersonal();

    void create(String firstName, String lastName, int bossId, int exp, int salary, int jobId, String username, String password);

    void update(int id, String lastName, int bossId, int exp, int salary, int jobId);

    void delete(int id);

    Personal getById(int id);

    Personal getByName(String name);
}
