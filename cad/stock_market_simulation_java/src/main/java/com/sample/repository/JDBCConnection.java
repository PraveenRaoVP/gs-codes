package com.sample.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
    private static JDBCConnection jdbcConnection;

    private JDBCConnection() {

    }

    public static JDBCConnection getInstance() {
        if (jdbcConnection == null) {
            jdbcConnection = new JDBCConnection();
        }
        return jdbcConnection;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onedha", "root", "mysql@123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
