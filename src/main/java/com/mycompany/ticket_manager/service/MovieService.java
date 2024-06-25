/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.service;

import com.mycompany.ticket_manager.model.Movie;
import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.repository.MovieRepository;
import com.mycompany.ticket_manager.util.Timestamp;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author chien
 */
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService() {
        this.movieRepository = new MovieRepository();
    }

    public Response<List<Movie>> getAllMovie() {
        try {
            ResultSet resultSet = this.movieRepository.fetchAllData();
            if (resultSet == null) {
                return new Response<>("Lỗi truy vấn dữ liệu");
            }

            List listMovie = new ArrayList<Movie>();

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getString("id"));
                movie.setName(resultSet.getString("name"));
                movie.setAge(resultSet.getInt("age"));
                movie.setMinPrice(resultSet.getInt("minPrice"));
                movie.setTime(resultSet.getInt("time"));
                movie.setHideAt(resultSet.getLong("hideAt"));
                movie.setStrHideAt(movie.getHideAt() == 0 ? null : Timestamp.convertTimeStampToString(movie.getHideAt(), "HH:mm:ss dd-MM-yyyy"));
                movie.setCreateAt(resultSet.getLong("createAt"));
                movie.setStrCreateAt(movie.getCreateAt() == 0 ? null : Timestamp.convertTimeStampToString(movie.getCreateAt(), "HH:mm:ss dd-MM-yyyy"));

                movie.setImage(resultSet.getString("image"));
                listMovie.add(movie);
            }

            if (listMovie.size() == 0) {
                return new Response<>("Không có dữ liệu");
            }

            return new Response<List<Movie>>().ok(listMovie);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<List<Object>> stopMovie(String id) {
        try {
            if (id.equals("")) {
                return new Response<>("Không tìm thấy id");
            }
            long time = Timestamp.getNowTimeStamp();
            int updated = this.movieRepository.setHideAt(id, time);
            if (updated == -1) {
                return new Response<>("Lỗi truy vấn");

            }
            if (updated == 0) {
                return new Response<>("Lỗi cập nhật thôngt tin");
            }

            return new Response<List<Object>>().ok(new ArrayList<Object>(Arrays.asList(Timestamp.convertTimeStampToString(time, "HH:mm:ss dd-MM-yyyy"), time)));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<Movie> editMovie(Movie movie) {
        try {
            if (movie.getId().equals("")) {
                return new Response<>("Không tìm thấy id");
            }

            if (movie.getName().trim().length() != movie.getName().length()) {
                return new Response<>("Tên phim không chứa khoảng trắng đầu cuối");
            }

            if (movie.getAge() != 1 && movie.getAge() != 16 && movie.getAge() != 18) {
                return new Response<>("Tuổi xem phim không hợp lệ");
            }

            if (movie.getMinPrice() <= 0) {
                return new Response<>("Giá vé lớn hơn 0");
            }

            if (movie.getTime() <= 0) {
                return new Response<>("Thời lượng phim quá ngắn");
            }

            int updated = this.movieRepository.updateMovie(movie);
            if (updated == -1) {
                return new Response<>("Lỗi truy vấn");

            }
            if (updated == 0) {
                return new Response<>("Lỗi cập nhật thôngt tin");
            }
            return new Response<Movie>().ok(movie);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<List<Movie>> findMovie(String id, String name) {
        try {
            id = id.trim();
            name = name.trim();
            ResultSet resultSet;
            if (id.length() > 0 && name.length() > 0) {
                resultSet = this.movieRepository.find(id, name);
            } else if (id.length() > 0) {
                resultSet = this.movieRepository.findById(id);

            } else if (name.length() > 0) {
                resultSet = this.movieRepository.findByName(id);
            } else {
                return new Response<>("Không thấy dữ liệu tìm kiếm");
            }

            List list = new ArrayList<Movie>();

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getString("id"));
                movie.setName(resultSet.getString("name"));
                movie.setAge(resultSet.getInt("age"));
                movie.setMinPrice(resultSet.getInt("minPrice"));
                movie.setTime(resultSet.getInt("time"));
                movie.setHideAt(resultSet.getLong("hideAt"));
                movie.setStrHideAt(movie.getHideAt() == 0 ? null : Timestamp.convertTimeStampToString(movie.getHideAt(), "HH:mm:ss dd-MM-yyyy"));
                movie.setCreateAt(resultSet.getLong("createAt"));
                movie.setStrCreateAt(movie.getCreateAt() == 0 ? null : Timestamp.convertTimeStampToString(movie.getCreateAt(), "HH:mm:ss dd-MM-yyyy"));

                movie.setImage(resultSet.getString("image"));
                list.add(movie);
            }

            if (list.size() == 0) {
                return new Response<>("Không có dữ liệu");
            }

            return new Response<List<Movie>>().ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<List<Movie>> findStatus(boolean status) {
        try {
            ResultSet resultSet;
            if (!status) {
                resultSet = this.movieRepository.getAllMovieStoped();
            } else {
                resultSet = this.movieRepository.getAllMoviePlaying();
            }
            List list = new ArrayList<Movie>();

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getString("id"));
                movie.setName(resultSet.getString("name"));
                movie.setAge(resultSet.getInt("age"));
                movie.setMinPrice(resultSet.getInt("minPrice"));
                movie.setTime(resultSet.getInt("time"));
                movie.setHideAt(resultSet.getLong("hideAt"));
                movie.setStrHideAt(movie.getHideAt() == 0 ? null : Timestamp.convertTimeStampToString(movie.getHideAt(), "HH:mm:ss dd-MM-yyyy"));
                movie.setCreateAt(resultSet.getLong("createAt"));
                movie.setStrCreateAt(movie.getCreateAt() == 0 ? null : Timestamp.convertTimeStampToString(movie.getCreateAt(), "HH:mm:ss dd-MM-yyyy"));

                movie.setImage(resultSet.getString("image"));
                list.add(movie);
            }

            if (list.size() == 0) {
                return new Response<>("Không có dữ liệu");
            }

            return new Response<List<Movie>>().ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

}
