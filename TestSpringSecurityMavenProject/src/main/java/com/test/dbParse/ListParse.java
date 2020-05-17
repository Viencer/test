package com.test.dbParse;

import com.test.model.Personal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListParse {

    private static Logger logger = Logger.getLogger(ListParse.class);

    private static Personal personal;

    @Autowired
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public static List<Personal> getAllPersonal(ResultSet resultSet) {
        List<Personal> personals = new ArrayList<Personal>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("PERSONAL_ID");
                String first_name = resultSet.getNString("FIRST_NAME");
                String last_name = resultSet.getString("LAST_NAME");
                int jobId = resultSet.getInt("JOB_ID");
                int experience = resultSet.getInt("EXPERIENCE");
                int boss_id = resultSet.getInt("BOSS_ID");
                int salary = resultSet.getInt("SALARY");
                personal = new Personal(id, first_name, last_name, jobId, boss_id, salary, experience);
                personals.add(personal);
            }
        } catch (SQLException e) {
            logger.error("SQL error in getAllPersonal() method. ListParse.class");
        }
        return personals;
    }
}
