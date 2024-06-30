/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.repository;

import com.mycompany.ticket_manager.helper.ConnectMySQL;
import com.mycompany.ticket_manager.model.Seat;
import com.mycompany.ticket_manager.model.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chien
 */
public class SeatRepository {

    Connection connection;
    PreparedStatement preparedStatement;

    public SeatRepository() {
        try {
            this.connection = ConnectMySQL.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection = null;
        }
    }

    public ResultSet getChairsBought(String idCalendar) {
        String sql = "SELECT seat.location FROM seat JOIN calendar ON calendar.id = seat.idCalendar WHERE seat.idCalendar = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idCalendar);
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
    public ResultSet getSeat(String id) {
        String sql = "select location from seat where idTicket = ?";
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

    public int insertListSeat(List<Seat> dataList) {
        String sql = "INSERT INTO seat(idTicket, location, idCalendar) values(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false); 
            for (Seat data : dataList) {
                preparedStatement.setString(1, data.getIdTicket());
                preparedStatement.setString(2, data.getLocation());
                preparedStatement.setString(3, data.getIdCalendar());
                preparedStatement.addBatch(); // Add to batch
            }
            int[] rowsInserted = preparedStatement.executeBatch();
            for(int i:rowsInserted){
                if(i <= 0){
                    connection.rollback();
                    throw new SQLException("Thêm không thành công ít nhất 1 chỗ");
                }
            }
            connection.commit();
            return 1;
        } catch (SQLException e) {
            System.out.println("here"); 
            e.printStackTrace();
            System.out.println("Lỗi sql");
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
