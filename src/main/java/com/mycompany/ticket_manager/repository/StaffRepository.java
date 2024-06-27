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
    
    public ResultSet getStaffById(String id) {
        String sql = "select * from staff where idnv = ?";
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

    public int addStaff(Staff staff) {
        String sql = "INSERT INTO staff(idnv, name, sdt, email, sex, `rank`, password) values(?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, staff.getIdnv());
            preparedStatement.setString(2, staff.getName());
            preparedStatement.setString(3, staff.getSdt());
            preparedStatement.setString(4, staff.getEmail());
            preparedStatement.setInt(5, staff.getSex());
            preparedStatement.setString(6, staff.getRank());
            preparedStatement.setString(7, staff.getPassword());
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
    
    public ResultSet getAll(){
        String sql = "select * from staff";
        try {
            preparedStatement = connection.prepareStatement(sql);
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
    
    public ResultSet find(String id, String name){
         String sql = "select * from staff where idnv like '%"+id+"%' or name like '%"+name+"%'";
         System.out.println(sql); 
        try {
            preparedStatement = connection.prepareStatement(sql);
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
    
    public ResultSet findById(String id){
         String sql = "select * from staff where idnv like '%"+id+"%'";
         System.out.println(sql); 
        try {
            preparedStatement = connection.prepareStatement(sql);
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
    
    public ResultSet findByName(String name){
         String sql = "select * from staff where name like '%"+name+"%'";
         System.out.println(sql); 
        try {
            preparedStatement = connection.prepareStatement(sql);
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
    
    public int setBlockAt(String id, long time){
        String sql = "update staff set blockAt = ? where idnv = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, time);
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
    
    
    public int updatePassword(String id, String password) {
        String sql = "update staff set password = ? where idnv = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, id);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi sql");
            return -1;
        }
    }
    
    public int updateStaff(Staff staff) {
        String sql = "update staff set name = ?, sdt = ?, email = ?, sex = ? where idnv = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getSdt());
            preparedStatement.setString(3, staff.getEmail());
            preparedStatement.setInt(4, staff.getSex());
            preparedStatement.setString(5, staff.getIdnv());
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi sql");
            return -1;
        }
    }
}
