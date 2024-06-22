/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.repository;

import com.mycompany.ticket_manager.helper.ConnectMySQL;
import com.mycompany.ticket_manager.model.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author chien
 */
public class StaffRepository {

    Connection connection;
    PreparedStatement preparedStatement;

    public StaffRepository() {
        try {
            this.connection = ConnectMySQL.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection = null;
        }
    }

    public ResultSet getStaffByEmail(String email) {
        String sql = "select * from staff where email = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            return preparedStatement.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Lỗi sql"); 
            return null;
        }catch(Exception e){
            System.out.println("Lỗi"); 
            e.printStackTrace();
            return null;
        }
//        finally{
//            try {
//                if (preparedStatement != null) preparedStatement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//                System.out.println("Lỗi đóng"); 
//            }
//        }
    }
}
