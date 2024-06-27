/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.repository;

import com.mycompany.ticket_manager.helper.ConnectMySQL;
import com.mycompany.ticket_manager.model.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author chien
 */
public class TicketRepository {
    Connection connection;
    PreparedStatement preparedStatement;

    public TicketRepository() {
        try {
            this.connection = ConnectMySQL.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection = null;
        }
    }
    
    public int insertTicket(Ticket ticket) {
        String sql = "insert into ticket(id, idcalendar, name, numPerson, numPopcorn, numWater, priceTicket, pricePopcorn, priceWater, email, createBy, createAt) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ticket.getId());
            preparedStatement.setString(2, ticket.getCalendar());
            preparedStatement.setString(3, ticket.getName());
            preparedStatement.setInt(4, ticket.getNumPerson());
            preparedStatement.setInt(5, ticket.getNumPopcorn());
            preparedStatement.setInt(6, ticket.getNumWater());
            preparedStatement.setInt(7, ticket.getPriceTicket());
            preparedStatement.setInt(8, ticket.getPricePopcorn());
            preparedStatement.setInt(9, ticket.getPriceWater());
            preparedStatement.setString(10, ticket.getEmail());
            preparedStatement.setString(11, ticket.getCreateBy());
            preparedStatement.setLong(12, ticket.getCreateAt());

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
