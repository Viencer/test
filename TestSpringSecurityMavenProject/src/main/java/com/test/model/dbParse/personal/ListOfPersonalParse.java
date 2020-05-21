package com.test.model.dbParse.personal;

import com.test.model.Personal;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ListOfPersonalParse {

    private static Logger logger = Logger.getLogger(ListOfPersonalParse.class);

    private Personal personal;


    public List<Personal> getAllPersonal(ResultSet resultSet) {
        List<Personal> personals = new ArrayList<Personal>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("PERSONAL_ID");
                String first_name = resultSet.getNString("FIRST_NAME");
                String last_name = resultSet.getString("LAST_NAME");
                int jobId = resultSet.getInt("JOB_ID");
                int commissions = resultSet.getInt("PREMIUM");
                int boss_id = resultSet.getInt("BOSS_ID");
                int salary = resultSet.getInt("SALARY");
                int department_id = resultSet.getInt("DEPARTMENT_ID");
                int patient_id = resultSet.getInt("PATIENT_ID");
                personal = new Personal(id, first_name, last_name, jobId, boss_id, salary, commissions, department_id, patient_id);
                personals.add(personal);
            }
        } catch (SQLException e) {
            logger.error("error in getAllPersonal() method. ListOfPersonalParse.class");
        }
        return personals;
    }
}
