/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.repository;

import com.mycompany.ticket_manager.helper.ConnectMySQL;
import com.mycompany.ticket_manager.model.Movie;
import com.mycompany.ticket_manager.model.Seat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author chien
 */
public class MovieRepository {

    Connection connection;
    PreparedStatement preparedStatement;

    public MovieRepository() {
        try {
            this.connection = ConnectMySQL.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection = null;
        }
    }
    
    public ResultSet getMovieById(String id) {
        String sql = "select * from movie where id = ?";
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
    
    public ResultSet fetchAllDataOk() {
        String sql = "select * from movie where movie.hideAt is null order by createAt";
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
    
    public ResultSet fetchAllData() {
        String sql = "select * from movie order by createAt";
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

    public int setHideAt(String id, long time) {
        String sql = "update movie set hideAt = ? where id = ?";
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

    public int updateMovie(Movie movie) {
        String sql = "update movie set name = ?, age = ?, minPrice = ?, time = ? where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, movie.getName());
            preparedStatement.setInt(2, movie.getAge());
            preparedStatement.setInt(3, movie.getMinPrice());
            preparedStatement.setInt(4, movie.getTime());
            preparedStatement.setString(5, movie.getId());

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

    public ResultSet find(String id, String name) {
        String sql = "select * from movie where id like '%" + id + "%' or name like '%" + name + "%' order by createAt";
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

    public ResultSet findById(String id) {
        String sql = "select * from movie where id like '%" + id + "%' order by createAt";
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
    
    public ResultSet getMovieByName(String name) {
        String sql = "select * from movie where name = ? order by createAt";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
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

    public ResultSet findByName(String name) {
        String sql = "select * from movie where name like '%" + name + "%' order by createAt";
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

    public ResultSet getAllMoviePlaying() {
        String sql = "select * from movie where hideAt is null order by createAt";
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

    public ResultSet getAllMovieStoped() {
        String sql = "select * from movie where hideAt is not null order by createAt";

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
    
    public ResultSet getMovieInDate(long start, long end){
         String sql = "SELECT movie.id, movie.name FROM movie JOIN calendar ON movie.id = calendar.idMovie WHERE calendar.cancleAt IS NULL AND movie.hideAt IS NULL AND calendar.time BETWEEN ? AND ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, start);
            preparedStatement.setLong(2, end);
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
    
    public int insertListMovie(List<Movie> dataList, long now) {
        String sql = "INSERT INTO movie(id, name, age, minPrice, createAt, time) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false); 
            for (Movie data : dataList) {
                preparedStatement.setString(1, data.getId());
                preparedStatement.setString(2, data.getName());
                preparedStatement.setInt(3, data.getAge());
                preparedStatement.setInt(4, data.getMinPrice());
                preparedStatement.setLong(5, now);
                preparedStatement.setInt(6, data.getTime());
                preparedStatement.addBatch(); // Add to batch
            }
            int[] rowsInserted = preparedStatement.executeBatch();
            for(int i:rowsInserted){
                if(i <= 0){
                    connection.rollback();
                    throw new SQLException("Lỗi thêm ít nhất 1 dữ liệu");
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
