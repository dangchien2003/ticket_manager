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

    public ResultSet getAllCalendarOk(long start, long end, String room) {
        String sql = "select calendar.id from calendar join movie on movie.id = calendar.idMovie where calendar.room = ? and calendar.cancleAt is null and (calendar.time + movie.time*60) between ? and ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, room);
            preparedStatement.setLong(2, start);
            preparedStatement.setLong(3, end);
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

    public ResultSet getCalendar(long start, long end, String room) {
        String sql = "SELECT calendar.time as timeCalendar, calendar.room,  movie.name, movie.time as timeMovie, calendar.id FROM calendar join movie on calendar.idMovie = movie.id where calendar.time between ? and ? and calendar.room = ? and calendar.cancleAt is null";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, start);
            preparedStatement.setLong(2, end);
            preparedStatement.setString(3, room);
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

    public int addCalendar(String idCalendar, String movie, long timePlay, String roomPlay) {
        String sql = "insert into calendar(id, idMovie, time, room) values(?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idCalendar);
            preparedStatement.setString(2, movie);
            preparedStatement.setLong(3, timePlay);
            preparedStatement.setString(4, roomPlay);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi sql");
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int updateCalendar(String idCalendar, String movie, long timePlay, String roomPlay) {
        String sql = "update calendar set idMovie = ?, time = ?, room = ? where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, movie);
            preparedStatement.setLong(2, timePlay);
            preparedStatement.setString(3, roomPlay);
            preparedStatement.setString(4, idCalendar);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi sql");
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int setCancleAt(String id, long cancleAt) {
        String sql = "update calendar set cancleAt = ? where id = ? and cancleAt is null";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, cancleAt);
            preparedStatement.setString(2, id);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi sql");
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
