package com.lumusitech.project.zonafit.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionCreator {
    public static Connection getConnection() {
        Connection connection = null;
        var url = "jdbc:mysql://localhost:3306/zona_fit_db";
        var user = "lumusitech";
        var password = "admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.err.println("Error at trying connect to DB: " + e.getMessage());
        }

        return connection;
    }

    public static void main(String[] args) {
        var connection = ConnectionCreator.getConnection();
        if (connection != null) System.out.println("Successful connection " + connection);
        else System.out.println("Error DB connection");
    }
}
