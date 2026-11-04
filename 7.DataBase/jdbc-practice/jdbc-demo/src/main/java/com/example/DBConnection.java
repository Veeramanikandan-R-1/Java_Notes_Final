package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
        "jdbc:mysql://localhost:3306/jdbc_demo";

    private static final String USER = "jdbc_user";

    private static final String PASSWORD = "jdbc123";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
            URL,
            USER,
            PASSWORD
        );
    }
}