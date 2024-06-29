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
public class StatisticalRepository {

    Connection connection;
    PreparedStatement preparedStatement;

    public StatisticalRepository() {
        try {
            this.connection = ConnectMySQL.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection = null;
        }
    }

    public ResultSet revenue(long timeStart, long timeEnd) {
        String sql = "select createAt, priceTicket,  priceWater, pricePopcorn from ticket where createAt between ? and ? order by createAt";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, timeStart);
            preparedStatement.setLong(2, timeEnd);
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
    public ResultSet movie(long timeStart, long timeEnd, String movie) {
        String sql = "select ticket.createAt, priceTicket from ticket join calendar on calendar.id = ticket.idCalendar join movie on calendar.idMovie = movie.id where ticket.createAt between ? and ? and idMovie = ? order by ticket.createAt";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, timeStart);
            preparedStatement.setLong(2, timeEnd);
            preparedStatement.setString(3, movie);
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
