package com.test.dbParse;

import com.test.model.Personal;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FindByParse {

    private static Personal personal;

    @Autowired
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public static Personal getPersonalBy(ResultSet resultSet) {
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
            }
        } catch (SQLException e) {
            System.out.println("SQL EXEPTION");
            e.printStackTrace();
        }
        return personal;
    }
}
