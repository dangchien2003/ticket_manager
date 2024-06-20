/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chien
 */
public class ConnectDatabase {
//    private static String url = "jdbc:sqlserver://localhost;databaseName=JAVA_QL_NHA_TRO;user=sa;password=chienkoi123;encrypt=true;trustServerCertificate=true";
    private static final String url = "jdbc:mysql://localhost:3306/ticket_manager";
    private static final String username = "root";
    private static final String password = "";
    
    public static Connection getConnecttion(){
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
