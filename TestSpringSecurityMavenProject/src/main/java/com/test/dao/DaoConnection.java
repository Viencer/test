package com.test.dao;

import javax.sql.DataSource;

public interface DaoConnection {
    void connect();
    void disconnect();

    DataSource getDataSource();
}
