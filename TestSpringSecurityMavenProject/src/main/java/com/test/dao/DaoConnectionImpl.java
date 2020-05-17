package com.test.dao;


import com.test.dbParse.FindByParse;
import com.test.dbParse.ListParse;
import com.test.model.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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


public class DaoConnectionImpl implements DaoConnection, DaoFind, DaoChange {


    private FindByParse findByParse;
    private DataSource dataSource;
    private ListParse listParse;
    private Personal personal;

    @Autowired
    public void setFindByParse(FindByParse findByParse) {
        this.findByParse = findByParse;
    }


    @Autowired
    public void setListParse(ListParse listParse) {
        this.listParse = listParse;
    }


    @Autowired
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static DaoConnectionImpl oracleDaoConnection;
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
            System.out.println("Connect is ok");
        } catch (SQLException | NamingException e) {
            System.out.println("CONNECTION ERROR");
            e.printStackTrace();
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
                System.out.println("closed");
            }
        } catch (SQLException | NamingException e) {
            System.out.println("DISCONNECTION ERROR");
            e.printStackTrace();
        }
    }

    @Override
    public List<Personal> selectAllPersonal() throws SQLException {
        connect();
        statement = connection.prepareStatement("SELECT * FROM LAB3_PERSONAL");
        resultSet = statement.executeQuery();
        personals = listParse.getAllPersonal(resultSet);
        disconnect();
        return personals;
    }

    @Override
    public void update(int id, String lastName, int bossId, int exp, int salary, int jobId) throws SQLException {
        connect();
        statement = connection.prepareStatement("UPDATE LAB3_PERSONAL" +
                " SET LAST_NAME = ?, BOSS_ID = ?, EXPERIENCE =?, SALARY = ?, JOB_ID = ? WHERE PERSONAL_ID = ?");
        statement.setString(1, lastName);
        statement.setInt(2, bossId);
        statement.setInt(3, exp);
        statement.setInt(4, salary);
        statement.setInt(5, jobId);
        statement.setInt(6, id);
        resultSet = statement.executeQuery();
        disconnect();
    }

    @Override
    public void delete(int id) throws SQLException {
        connect();
        statement = connection.prepareStatement("DELETE LAB3_PERSONAL WHERE PERSONAL_ID = ?");
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        disconnect();
    }

    @Override
    public void create(String firstName, String lastName, int bossId, int exp, int salary, int jobId, String username, String password) throws SQLException {
        connect();
        statement = connection.prepareStatement("INSERT INTO lab3_personal " +
                "(personal_id, first_name, last_name, boss_id, job_id, experience, salary)  " +
                "VALUES (LAB3_PERSONAL_SEQ.nextval, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setInt(3, bossId);
        statement.setInt(4, jobId);
        statement.setInt(5, exp);
        statement.setInt(6, salary);
        resultSet = statement.executeQuery();
        statement = connection.prepareStatement("INSERT INTO lab3_Roles " +
                "(personal_id, user_name, password, role, enabled) " +
                "VALUES (LAB3_ROLE_SEQ.nextval, ?, ?, 'ROLE_USER', 1)");
        statement.setString(1, username);
        statement.setString(2, password);
        resultSet = statement.executeQuery();
        disconnect();
    }

    @Override
    public Personal findById(int id) throws SQLException {
        connect();
        statement = connection.prepareStatement("SELECT * FROM LAB3_PERSONAL WHERE PERSONAL_ID = ?");
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        personal = findByParse.getPersonalBy(resultSet);
        disconnect();
        return personal;
    }

    @Override
    public Personal getByName(String name) throws SQLException {
        connect();
        statement = connection.prepareStatement("SELECT * FROM LAB3_PERSONAL P, LAB3_ROLES R WHERE P.PERSONAL_ID = R.PERSONAL_ID AND USER_NAME = ?");
        statement.setString(1, name);
        resultSet = statement.executeQuery();
        personal = findByParse.getPersonalBy(resultSet);
        disconnect();
        return personal;
    }
}
