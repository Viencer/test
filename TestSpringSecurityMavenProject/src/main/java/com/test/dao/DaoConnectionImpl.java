package com.test.dao;

import com.test.dbParse.patient.FindByParsePatient;
import com.test.dbParse.patient.ListOfPatient;
import com.test.dbParse.personal.FindByParse;
import com.test.dbParse.personal.ListOfPersonalParse;
import com.test.model.Patient;
import com.test.model.Personal;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


@Component("dao")
@Scope(value = "singleton")
@PropertySource("classpath:resources.properties")
public class DaoConnectionImpl implements DaoConnection, DaoFind, DaoChange {

    private static Logger logger = Logger.getLogger(DaoConnectionImpl.class);

    private DataSource dataSource;
    private static DaoConnectionImpl oracleDaoConnection;
    private Personal personal;
    private Patient patient;
    private Context context;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;
    private List<Personal> personals = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();

    public static DaoConnectionImpl getInstance() {
        if (oracleDaoConnection != null) {
            return oracleDaoConnection;
        }
        return new DaoConnectionImpl();
    }

    public DataSource getDataSource() {
        connect();
        return dataSource;
    }

    //CONNECT
    @Override
    public void connect() {
        try {
            Hashtable hashtable = new Hashtable();
            hashtable.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            hashtable.put(Context.PROVIDER_URL, "t3://localhost:7001");
            context = new InitialContext(hashtable);
            dataSource = (DataSource) context.lookup("lab3");
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

    public void dataCreate() {


    }

    @Override
    public List<Personal> selectAllPersonal() {
        try {
            connect();
            statement = connection.prepareStatement("SELECT * FROM LAB3MU_PERSONAL");
            resultSet = statement.executeQuery();
            personals = ListOfPersonalParse.getAllPersonal(resultSet);
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
            patients = ListOfPatient.getAllPatient(resultSet);
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
                    " SET LAST_NAME = ?, BOSS_ID = ?, COMMISSIONS = ?, SALARY = ?, JOB_ID = ?, DEPARTMENT_ID = ?, " +
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
    public void createPersonal(String firstName, String lastName, int bossId, int exp, int salary, int jobId,
                               int department_id, Integer patient_id) {
        try {
            connect();
            statement = connection.prepareStatement("INSERT INTO LAB3MU_PERSONAL " +
                    "(personal_id, first_name, last_name, boss_id, job_id, commissions, salary, department_id, patient_id)  " +
                    "VALUES (LAB3MU_PERSONAL_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, bossId);
            statement.setInt(4, jobId);
            statement.setInt(5, exp);
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
    public void createPersonalData(String username, String password) {
        try {
            connect();
            statement = connection.prepareStatement("INSERT INTO LAB3MU_USER_DATA " +
                    "(personal_id, user_name, password, role, enable) " +
                    "VALUES (LAB3MU_USER_DATA_SEQ.nextval, ?, ?, 'ROLE_DOCTOR', 1)");
            statement.setString(1, username);
            statement.setString(2, password);
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
            personals = ListOfPersonalParse.getAllPersonal(resultSet);
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
            personal = FindByParse.getPersonalBy(resultSet);
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
            patient = FindByParsePatient.getPatientBy(resultSet);
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
            personal = FindByParse.getPersonalBy(resultSet);
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
            personals = ListOfPersonalParse.getAllPersonal(resultSet);
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
            patients = ListOfPatient.getAllPatient(resultSet);
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
            patients = ListOfPatient.getAllPatient(resultSet);
            return patients;
        } catch (SQLException e) {
            logger.error("error in getByName() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return patients;
    }
}
