/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author chien
 */
public class ExcuteQuery {
    Connection connection;
    PreparedStatement preparedStatement = null;
    public ExcuteQuery(){
        try {
            this.connection = ConnectMySQL.getConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            connection = null;
        }
    }
    
    public ResultSet getResultSet(String sql){
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Lỗi sql"); 
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
     public int editData(String sql){
        try {
            preparedStatement = connection.prepareStatement(sql);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Lỗi sql"); 
            return -1;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }finally{
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
