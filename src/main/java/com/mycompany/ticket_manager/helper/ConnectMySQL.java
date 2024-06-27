/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.helper;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chien
 */
public class ConnectMySQL {

    private static String URL = "jdbc:mysql://localhost:3306/ticket_manager";
    private static String USER = "root";
    private static String PASSWORD = "";

    private static void setInfo() {
        Dotenv dotenv = Dotenv.load();
        if (dotenv.get("ACTIVE").toLowerCase().equals("dev") ) {
            URL = "jdbc:mysql://" + dotenv.get("DB_HOST") + ":" + dotenv.get("DB_PORT") + "/" + dotenv.get("DB_DATABASE");
            USER = dotenv.get("DB_USER");
            PASSWORD = dotenv.get("DB_PASSWORD");
        } else if (dotenv.get("ACTIVE").toLowerCase().equals("prod")) {
            URL = "jdbc:mysql://" + dotenv.get("DB_HOST_PRODUCT") + ":" + dotenv.get("DB_PORT_PRODUCT") + "/" + dotenv.get("DB_DATABASE_PRODUCT");
            USER = dotenv.get("DB_USER_PRODUCT");
            PASSWORD = dotenv.get("DB_PASSWORD_PRODUCT");

        }
    }

    public static Connection getConnection() throws SQLException {
        setInfo();
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
