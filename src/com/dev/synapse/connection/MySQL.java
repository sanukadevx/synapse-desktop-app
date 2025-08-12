package com.dev.synapse.connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    private static final String DATABASE = "synapse_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Collidedever@20071216";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + DATABASE, USERNAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeSearch(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    public static void executeIUD(String query) {
        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeIUDReturn(String query, int RETURN_GENERATED_KEYS) {
        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + DATABASE, USERNAME, PASSWORD);
    }

}
