package com.test.dbParse.patient;

import com.test.model.Patient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FindByParsePatient {

    private static Logger logger = Logger.getLogger(FindByParsePatient.class);

    private static Patient patient;

    @Autowired
    public void setPersonal(Patient patient) {
        this.patient = patient;
    }

    public static Patient getPatientBy(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                int patient_id = resultSet.getInt("PATIENT_ID");
                String first_name = resultSet.getString("FIRST_NAME");
                String last_name = resultSet.getString("LAST_NAME");
                String position = resultSet.getString("POSITION");
                String phone = resultSet.getString("PHONE");
                String address = resultSet.getString("ADDRESS");
                int diagnosis_id = resultSet.getInt("DIAGNOSIS_ID");
                int medicine_id = resultSet.getInt("MEDICINE_ID");
                patient = new Patient(patient_id, first_name, last_name, position, phone, address, diagnosis_id, medicine_id);
            }
        } catch (SQLException e) {
            logger.error("error in getPersonalBy() method. FindByParse.class");
        }
        return patient;
    }
}
