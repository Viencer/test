package com.test.dbParse.personal;

import com.test.model.Personal;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FindByParse {

    private static Logger logger = Logger.getLogger(FindByParse.class);

    private static Personal personal;


    public static Personal getPersonalBy(ResultSet resultSet) {
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
            }
        } catch (SQLException e) {
            logger.error("error in getPersonalBy() method. FindByParse.class");
        }
        return personal;
    }
}
