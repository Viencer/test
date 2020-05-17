package com.test.dao;

import javax.naming.NamingException;
import java.sql.SQLException;

public interface DaoConnection {
    void connect() throws SQLException, NamingException, ClassNotFoundException;
    void disconnect();
}
