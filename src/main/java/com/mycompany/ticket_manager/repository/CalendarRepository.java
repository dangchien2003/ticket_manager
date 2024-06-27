/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.repository;

import com.mycompany.ticket_manager.helper.ConnectMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author chien
 */
public class CalendarRepository {

    Connection connection;
    PreparedStatement preparedStatement;

    public CalendarRepository() {
        try {
            this.connection = ConnectMySQL.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection = null;
        }
    }

    public ResultSet getCalendar(String idMovie, long start) {
        String sql = "SELECT calendar.id, calendar.time FROM calendar JOIN movie ON movie.id = calendar.idMovie WHERE  idMovie = ? AND calendar.cancleAt IS NULL AND calendar.time + movie.time*60 - 1800  BETWEEN ? AND calendar.time + movie.time*60 - 1800";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idMovie);
            preparedStatement.setLong(2, start);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi sql");
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi");
            e.printStackTrace();
            return null;
        }

    }

    public ResultSet getRemaing(String id) {
        String sql = "SELECT movie.time as timeMovie, calendar.time as timeCalendar, calendar.room FROM calendar JOIN movie ON movie.id = calendar.idMovie WHERE calendar.id = ? limit 1";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi sql");
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi");
            e.printStackTrace();
            return null;
        }
    }
    
     public ResultSet getCalendar(String id) {
        String sql = "select * from calendar where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi sql");
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi");
            e.printStackTrace();
            return null;
        }
    }
    
    

}
