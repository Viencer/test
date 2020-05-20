package com.test.dao;

import com.test.dbParse.FindByParse;
import com.test.dbParse.ListParse;
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
    private Context context;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;
    private List<Personal> personals = new ArrayList<>();

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
            personals = ListParse.getAllPersonal(resultSet);
            return personals;
        } catch (SQLException e) {
            logger.error("error in selectAllPersonal() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
        return personals;
    }

    @Override
    public void update(int id, String lastName, int bossId, int com, int salary, int jobId, int department_id, Integer patient_id) {
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
    public void delete(int id) {
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
    public void create(String firstName, String lastName, int bossId, int exp, int salary, int jobId, String username,
                       String password, int department_id, Integer patient_id) {
        try {
            connect();
            statement = connection.prepareStatement("INSERT INTO LAB3MU_PERSONAL " +
                    "(personal_id, first_name, last_name, boss_id, job_id, commissions, salary, department_id, patient_id)  " +
                    "VALUES (LAB3_PERSONAL_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
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
            statement = connection.prepareStatement("INSERT INTO LAB3MU_USER_DATA " +
                    "(personal_id, user_name, password, role, enabled) " +
                    "VALUES (LAB3MU_USER_DATA_SEQ.nextval, ?, ?, 'ROLE_USER', 1)");
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("error in create() method. DaoConnectionImpl.Class");
        } finally {
            disconnect();
        }
    }

    @Override
    public Personal findById(int id) {
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
}
