package com.lumusitech.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/zona_fit_db";
        String user = "lumusitech";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Successful connection to MySQL!");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}