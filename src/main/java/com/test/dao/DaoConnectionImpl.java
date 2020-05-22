package com.test.dao;

import com.test.model.dbParse.otherTables.OtherTablesParse;
import com.test.model.dbParse.patient.FindByParsePatient;
import com.test.model.dbParse.patient.ListOfPatient;
import com.test.model.dbParse.personal.FindByParse;
import com.test.model.dbParse.personal.ListOfPersonalParse;
import com.test.model.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@PropertySource("classpath:resources.properties")
@Component("dao")
@Scope(value = "singleton")
public class DaoConnectionImpl implements DaoConnection, DaoFind, DaoChange {

    private static Logger logger = Logger.getLogger(DaoConnectionImpl.class);

    private DataSource dataSource;
    private Personal personal;
    private Patient patient;
    private Context context;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;
    private List<Personal> personals = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<Medicine> medicines = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();
    private List<Diagnosis> diagnoses = new ArrayList<>();
    private List<Jobs> jobs = new ArrayList<>();
    private List<Treatment> treatments = new ArrayList<>();

    private static String jndi;

    @Autowired
    public void setJndi(@Value("${hospital.contextFactory}") String jndi) {
        this.jndi = jndi;
    }

    private static String providerUrl;

    @Autowired
    public void setProviderUrl(@Value("${hospital.providerUrl}") String providerUrl) {
        this.providerUrl = providerUrl;
    }

    private static String contextData;

    @Autowired
    public void setContextData(@Value("${hospital.contextData}") String contextData) {
        this.contextData = contextData;
    }

    private OtherTablesParse otherTablesParse;

    @Autowired
    public void setOtherTablesParse(OtherTablesParse otherTablesParse) {
        this.otherTablesParse = otherTablesParse;
    }

    private FindByParsePatient findByParsePatient;

    @Autowired
    public void setFindByParsePatient(FindByParsePatient findByParsePatient) {
        this.findByParsePatient = findByParsePatient;
    }

    private ListOfPatient listOfPatient;

    @Autowired
    public void setListOfPatient(ListOfPatient listOfPatient) {
        this.listOfPatient = listOfPatient;
    }

    private FindByParse findByParse;

    @Autowired
    public void setFindByParse(FindByParse findByParse) {
        this.findByParse = findByParse;
    }

    private ListOfPersonalParse listOfPersonalParse;

    @Autowired
    public void setListOfPersonalParse(ListOfPersonalParse listOfPersonalParse) {
        this.listOfPersonalParse = listOfPersonalParse;
    }

    //CONNECT
    @Override
    public void connect() {
        try {
            Hashtable hashtable = new Hashtable();
            hashtable.put(Context.INITIAL_CONTEXT_FACTORY, jndi);
            hashtable.put(Context.PROVIDER_URL, providerUrl);
            context = new InitialContext(hashtable);
            dataSource = (DataSource) context.lookup(contextData);
            connection = dataSource.getConnection();
            logger.debug("connection is ok");
        } catch (SQLException | NamingException e) {
            logger.error("DaoConnectionImpl.Class. connection error " + e.getMessage());
        }
    }


    //DISCONNECT
    @Override
    public void disconnect() {
        try {
            connection.close();
            resultSet.close();
            statement.close();
            context.close();
            if (connection.isClosed()) {
                logger.debug("connection closed");
            }
        } catch (SQLException | NamingException e) {
            logger.error("DaoConnectionImpl.Class. disconnection error " + e.getMessage());
        }
    }

    @Override
    public DataSource getDataSource() {
        try {
            connect();
            return dataSource;
        } finally {
            try {
                connection.close();
                context.close();
            } catch (SQLException | NamingException e) {
                logger.error("DaoConnectionImpl.Class. disconnection error " + e.getMessage());
            }
        }
    }

    @PostConstruct
    public void dataCreate() {
        connect();
        try {
            // File drop = ResourceUtils.getFile("classpath:drop.sql");
            File create = ResourceUtils.getFile("classpath:db.sql");
            File insert = ResourceUtils.getFile("classpath:insert.sql");
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.setStopOnError(false);
            //scriptRunner.runScript(new BufferedReader(new FileReader(drop)));
            scriptRunner.runScript(new BufferedReader(new FileReader(create)));
            scriptRunner.runScript(new BufferedReader(new FileReader(insert)));
        } catch (NullPointerException | IOException e) {
            logger.error("Error in create database ");
        } finally {
            try {
                connection.close();
                context.close();
            } catch (NamingException | SQLException e) {
                logger.error("Error in create database ");
            }
        }
    }

    @Override
    public List<Personal> selectAllPersonal() {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_PERSONAL");
            resultSet = statement.executeQuery();
            personals = listOfPersonalParse.getAllPersonal(resultSet);
            return personals;
        } catch (SQLException e) {
            logger.error("error in selectAllPersonal() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return personals;
    }

    @Override
    public List<Patient> selectAllPatient() {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_PATIENT");
            resultSet = statement.executeQuery();
            patients = listOfPatient.getAllPatient(resultSet);
            return patients;
        } catch (SQLException e) {
            logger.error("error in selectAllPersonal() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return patients;
    }

    @Override
    public void updatePersonal(int id, String lastName, int bossId, int com, int salary, int jobId, int department_id, Integer patient_id) {
        try {
            connect();
            statement = connection.prepareStatement("UPDATE LAB3MU_PERSONAL" +
                    " SET LAST_NAME = ?, BOSS_ID = ?, PREMIUM = ?, SALARY = ?, JOB_ID = ?, DEPARTMENT_ID = ?, " +
                    "PATIENT_ID = ? WHERE PERSONAL_ID = ?");
            statement.setString(1, lastName);
            statement.setInt(2, bossId);
            statement.setInt(3, com);
            statement.setInt(4, salary);
            statement.setInt(5, jobId);
            statement.setInt(6, department_id);
            if (patient_id == 0) {
                statement.setNull(7, java.sql.Types.INTEGER);
            } else {
                statement.setInt(7, patient_id);
            }
            statement.setInt(8, id);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("error in update() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
    }

    @Override
    public void updatePatient(int id, int diagnosisId, int medicineId) {
        try {
            connect();
            statement = connection.prepareStatement("UPDATE LAB3MU_PATIENT" +
                    " SET DIAGNOSIS_ID = ?, MEDICINE_ID = ? WHERE PATIENT_ID = ?");
            statement.setInt(1, diagnosisId);
            statement.setInt(2, medicineId);
            statement.setInt(3, id);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("error in update() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
    }

    @Override
    public void deletePersonal(int id) {
        try {
            connect();
            statement = connection.prepareStatement("DELETE LAB3MU_PERSONAL WHERE PERSONAL_ID = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("error in delete() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
    }

    @Override
    public void deletePatient(int id) {
        try {
            connect();
            statement = connection.prepareStatement("DELETE LAB3MU_PATIENT WHERE PATIENT_ID = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("error in delete() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
    }

    @Override
    public void createPersonal(String firstName, String lastName, int bossId, int premium, int salary, int jobId,
                               int department_id, Integer patient_id) {
        try {
            connect();
            statement = connection.prepareStatement("INSERT INTO LAB3MU_PERSONAL " +
                    "(personal_id, first_name, last_name, boss_id, job_id, premium, salary, department_id, patient_id)  " +
                    "VALUES (LAB3MU_PERSONAL_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            if (bossId == 0) {
                statement.setNull(3, java.sql.Types.INTEGER);
            } else {
                statement.setInt(3, bossId);
            }
            statement.setInt(4, jobId);
            if (premium == 0) {
                statement.setNull(5, java.sql.Types.INTEGER);
            } else {
                statement.setInt(5, premium);
            }
            statement.setInt(6, salary);
            statement.setInt(7, department_id);
            if (patient_id == 0) {
                statement.setNull(8, java.sql.Types.INTEGER);
            } else {
                statement.setInt(8, patient_id);
            }
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("error in create() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
    }

    @Override
    public void createPatient(String firstName, String lastName, String position, int phone, String address, int diagnosisId, int medicineId) {
        try {
            connect();
            statement = connection.prepareStatement("INSERT INTO LAB3MU_PATIENT " +
                    "(PATIENT_ID, FIRST_NAME, LAST_NAME, POSITION, PHONE, ADDRESS, DIAGNOSIS_ID, MEDICINE_ID)  " +
                    "VALUES (LAB3MU_PATIENT_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, position);
            statement.setInt(4, phone);
            statement.setString(5, address);
            statement.setInt(6, diagnosisId);
            statement.setInt(7, medicineId);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("error in create() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
    }

    @Override
    public void createPersonalData(String username, String password, int jobId) {
        try {
            String role = null;
            switch (jobId) {
                case 1:
                    role = "ROLE_ADMIN";
                    break;
                case 2:
                    role = "ROLE_DOCTOR";
                    break;
                case 3:
                    role = "ROLE_INTERN";
                    break;
            }
            connect();
            statement = connection.prepareStatement("INSERT INTO LAB3MU_USER_DATA " +
                    "(personal_id, user_name, password, role, enable) " +
                    "VALUES (LAB3MU_USER_DATA_SEQ.nextval, ?, ?, ?, 1)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("error in createData() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
    }

    @Override
    public List<Personal> findByIdList(int id) {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_PERSONAL WHERE PERSONAL_ID = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            personals = listOfPersonalParse.getAllPersonal(resultSet);
            return personals;
        } catch (SQLException e) {
            logger.error("error in findById() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return personals;
    }

    @Override
    public Personal findByIdPersonal(int id) {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_PERSONAL WHERE PERSONAL_ID = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            personal = findByParse.getPersonalBy(resultSet);
            return personal;
        } catch (SQLException e) {
            logger.error("error in findById() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return personal;
    }

    @Override
    public Patient findByIdPatient(int id) {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_PATIENT WHERE PATIENT_ID = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            patient = findByParsePatient.getPatientBy(resultSet);
            return patient;
        } catch (SQLException e) {
            logger.error("error in findById() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return patient;
    }

    @Override
    public Personal getByName(String name) {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_PERSONAL P, LAB3MU_USER_DATA R WHERE P.PERSONAL_ID = R.PERSONAL_ID AND USER_NAME = ?");
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            personal = findByParse.getPersonalBy(resultSet);
            return personal;
        } catch (SQLException e) {
            logger.error("error in getByName() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return personal;
    }

    @Override
    public List<Personal> findByLastName(String lastName) {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_PERSONAL WHERE LAST_NAME = ?");
            statement.setString(1, lastName);
            resultSet = statement.executeQuery();
            personals = listOfPersonalParse.getAllPersonal(resultSet);
            return personals;
        } catch (SQLException e) {
            logger.error("error in getByName() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return personals;
    }

    @Override
    public List<Patient> getByIdPatientList(int id) {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_PATIENT WHERE PATIENT_ID = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            patients = listOfPatient.getAllPatient(resultSet);
            return patients;
        } catch (SQLException e) {
            logger.error("error in findById() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return patients;
    }

    @Override
    public List<Patient> findByLastNamePatient(String lastName) {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_PATIENT WHERE LAST_NAME = ?");
            statement.setString(1, lastName);
            resultSet = statement.executeQuery();
            patients = listOfPatient.getAllPatient(resultSet);
            return patients;
        } catch (SQLException e) {
            logger.error("error in getByName() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return patients;
    }

    @Override
    public List<Department> getAllDepartments() {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_DEPARTMENT");
            resultSet = statement.executeQuery();
            departments = otherTablesParse.getAllDepartments(resultSet);
            return departments;
        } catch (SQLException e) {
            logger.error("error in selectAllDepartments() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return departments;
    }

    @Override
    public List<Diagnosis> getAllDiagnosis() {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_DIAGNOSIS");
            resultSet = statement.executeQuery();
            diagnoses = otherTablesParse.getAllDiagnosis(resultSet);
            return diagnoses;
        } catch (SQLException e) {
            logger.error("error in getAllDiagnosis() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return diagnoses;
    }

    @Override
    public List<Jobs> getAllJobs() {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_JOBS");
            resultSet = statement.executeQuery();
            jobs = otherTablesParse.getAllJobs(resultSet);
            return jobs;
        } catch (SQLException e) {
            logger.error("error in getAllJobs() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return jobs;
    }

    @Override
    public List<Medicine> getAllMedicines() {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_MEDICINE");
            resultSet = statement.executeQuery();
            medicines = otherTablesParse.getAllMedicine(resultSet);
            return medicines;
        } catch (SQLException e) {
            logger.error("error in getAllMedicines() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return medicines;
    }

    @Override
    public List<Treatment> getAllTreatments() {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_TREATMENT");
            resultSet = statement.executeQuery();
            treatments = otherTablesParse.getAllTreatment(resultSet);
            return treatments;
        } catch (SQLException e) {
            logger.error("error in getAllTreatments() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return treatments;
    }
}
