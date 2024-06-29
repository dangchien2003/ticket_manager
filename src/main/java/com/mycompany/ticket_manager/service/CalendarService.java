/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.service;

import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.repository.CalendarRepository;
import com.mycompany.ticket_manager.repository.MovieRepository;
import com.mycompany.ticket_manager.util.NumberUtil;
import com.mycompany.ticket_manager.util.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chien
 */
public class CalendarService {

    CalendarRepository calendarRepository;
    MovieRepository movieRepository;

    public CalendarService() {
        this.calendarRepository = new CalendarRepository();
        this.movieRepository = new MovieRepository();
    }

    public Response<List<Map<String, String>>> getCalendarMovie(String idMovie, long timestamp) {
        try {
            long start = 0;
            long now = Timestamp.getNowTimeStamp();
            if (timestamp > now) {
                start = timestamp;
            }

            start = now;

            ResultSet resultSet = this.calendarRepository.getCalendar(idMovie, start);
            if (resultSet == null) {
                return new Response<>("Lỗi truy vấn");
            }

            List listCalendar = new ArrayList<Map<String, String>>();
            Map<String, String> map = new HashMap<>();
            map.put("time", "Chọn lịch chiếu");
            map.put("id", "0");
            listCalendar.add(map);
            while (resultSet.next()) {
                map = new HashMap<>();
                map.put("id", resultSet.getString("id"));
                map.put("time", Timestamp.convertTimeStampToString(resultSet.getLong("time"), "HH:mm dd-MM-yyyy"));

                listCalendar.add(map);
            }
            return new Response<List<Map<String, String>>>().ok(listCalendar);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<String> getRemaingTime(String idCalendar) {
        try {
            idCalendar = idCalendar.trim();
            if (idCalendar.equals("")) {
                return new Response<>("Id không hợp lệ");
            }

            ResultSet resultSet = this.calendarRepository.getRemaing(idCalendar);

            if (resultSet == null) {
                return new Response<>("Lỗi truy vấn dữ liệu");
            }

            resultSet.next();

            if (resultSet.getRow() == 0) {
                return new Response<>("Không có dữ liệu");
            }

            long timeMovie = resultSet.getLong("timeMovie");
            long timeCalendar = resultSet.getLong("timeCalendar");
            String room = resultSet.getString("room");
            long now = Timestamp.getNowTimeStamp();

            long endCalendar = (timeCalendar + timeMovie * 60);
            long remaining = endCalendar - now;

            if (remaining < timeMovie * 60 && remaining >= 0) {
                return new Response<String>().ok("P" + room + " " + remaining / 60 + "/" + timeMovie + " Phút");
            } else {
                return new Response<String>().ok("P" + room);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<List<Map<String, Object>>> getCalendar(long date, String selectedRoom) {
        try {
            if (date == 0 || selectedRoom.trim().equals("")) {
                return new Response<>("Thông tin không hợp lệ");
            }

            String dateFind = Timestamp.convertTimeStampToString(date, "dd-MM-yyyy");
            long start = Timestamp.convertTimeToTimestamp(dateFind, "dd-MM-yyyy");
            long end = start + 86400;
            String room = selectedRoom.split(" ")[1];

            ResultSet resultSet = this.calendarRepository.getCalendar(start, end, room);
            if (resultSet == null) {
                return new Response<>("Lỗi lấy dữ liệu");
            }

            List<Map<String, Object>> dataRes = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> data = new HashMap<>();
                data.put("nameMovie", resultSet.getString("name"));
                data.put("room", resultSet.getLong("room"));
                data.put("timeMovie", resultSet.getInt("timeMovie"));
                data.put("playAt", resultSet.getLong("timeCalendar"));
                data.put("datePlay", Timestamp.convertTimeStampToString(resultSet.getLong("timeCalendar"), "dd-MM-yyyy"));
                data.put("timeStart", Timestamp.convertTimeStampToString(resultSet.getLong("timeCalendar"), "HH:mm"));
                data.put("timeEnd", Timestamp.convertTimeStampToString(resultSet.getLong("timeCalendar") + resultSet.getInt("timeMovie") * 60, "HH:mm"));
                data.put("id", resultSet.getString("id"));
                dataRes.add(data);
            }
            if (dataRes.isEmpty()) {
                return new Response<>("Không có dữ liệu");
            }

            return new Response<List<Map<String, Object>>>().ok(dataRes);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<Map<String, Object>> checkCalendar(String playAt, String room, String movie, String idCalendar) {
        try {
            long timePlay = Timestamp.convertTimeToTimestamp(playAt, "HH:mm:ss dd-MM-yyyy");

            if (timePlay < Timestamp.getNowTimeStamp()) {
                return new Response<>("Thời gian chiếu không hợp lệ");
            }

            if (movie == null || movie.trim().length() == 0) {
                return new Response<>("Phim không hợp lệ");
            }

            ResultSet resultSet = this.movieRepository.getMovieById(movie);

            if (resultSet == null) {
                return new Response<>("Lỗi lấy dữ liệu");
            }

            resultSet.next();
            if (resultSet.getRow() == 0) {
                return new Response<>("Phim không tồn tại");
            }
            if (resultSet.getString("hideAt") != null) {
                return new Response<>("Phim đã bị dừng chiếu");
            }
            String nameMovie = resultSet.getString("name");
            int timeMovie = resultSet.getInt("time");
            String roomPlay = room.split(" ")[1];
            long timeEnd = timePlay + timeMovie * 60;

            resultSet = this.calendarRepository.getAllCalendarOk(timePlay - 15 * 60, timeEnd, roomPlay);

            if (resultSet == null) {
                return new Response<>("Lỗi lấy dữ liệu");
            }
            resultSet.next();
            if (resultSet.getRow() == 1) {
                if (idCalendar != null) {
                    if (!resultSet.getString("id").equals(idCalendar)) {
                        return new Response<>("Trùng lịch chiếu");
                    } else {
                        resultSet.next();
                        if (resultSet.getRow() == 2) {
                            return new Response<>("Trùng lịch chiếu");
                        }
                    }
                } else {
                    return new Response<>("Trùng lịch chiếu");

                }
            }
            return new Response<Map<String, Object>>().ok(new HashMap<>() {
                {
                    put("timePlay", timePlay);
                    put("roomPlay", roomPlay);
                    put("nameMovie", nameMovie);
                    put("timeMovie", timeMovie);
                    put("timeEnd", timeEnd);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<Map<String, Object>> updateCalendar(String id, String playAt, String room, String movie) {
        try {
            id = id.trim();
            if (id.length() == 0) {
                return new Response<>("Không tìm thấy id");
            }

            Response<Map<String, Object>> responseCheck = this.checkCalendar(playAt, room, movie, id);

            if (responseCheck.getSuccess() == false) {
                return responseCheck;
            }
            long timePlay = (long) responseCheck.getData().get("timePlay");
            String roomPlay = responseCheck.getData().get("roomPlay").toString();
            String nameMovie = responseCheck.getData().get("nameMovie").toString();
            int timeMovie = (int) responseCheck.getData().get("timeMovie");
            long timeEnd = (long) responseCheck.getData().get("timeEnd");

            int updated = this.calendarRepository.updateCalendar(id, movie, timePlay, roomPlay);

            if (updated == -1) {
                return new Response<>("Lỗi truy vấn dữ liệu");
            }

            if (updated == 0) {
                return new Response<>("Cập nhật thất bại");
            }

            return new Response<Map<String, Object>>().ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<Map<String, Object>> addCalendar(String playAt, String room, String movie) {
        try {
            Response<Map<String, Object>> responseCheck = this.checkCalendar(playAt, room, movie, null);

            if (responseCheck.getSuccess() == false) {
                return responseCheck;
            }
            long timePlay = (long) responseCheck.getData().get("timePlay");
            String roomPlay = responseCheck.getData().get("roomPlay").toString();
            String nameMovie = responseCheck.getData().get("nameMovie").toString();
            int timeMovie = (int) responseCheck.getData().get("timeMovie");
            long timeEnd = (long) responseCheck.getData().get("timeEnd");

            String idCalendar = "CALENDAR_" + Timestamp.getNowTimeStamp() + "_" + NumberUtil.genNumber(3);

            int inserted = this.calendarRepository.addCalendar(idCalendar, movie, timePlay, roomPlay);

            if (inserted == -1) {
                return new Response<>("Lỗi truy vấn dữ liệu");
            }

            if (inserted == 0) {
                return new Response<>("Thêm thất bại");
            }
            return new Response<Map<String, Object>>().ok(new HashMap<>() {
                {
                    put("nameMovie", nameMovie);
                    put("room", roomPlay);
                    put("timeMovie", timeMovie);
                    put("playAt", timePlay);
                    put("datePlay", Timestamp.convertTimeStampToString(timePlay, "dd-MM-yyyy"));
                    put("timeStart", Timestamp.convertTimeStampToString(timePlay, "HH:mm"));
                    put("timeEnd", Timestamp.convertTimeStampToString(timeEnd, "HH:mm"));
                    put("id", idCalendar);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<Boolean> cancleCalendar(String id) {
        try {
            id = id.trim();
            if (id.length() == 0) {
                return new Response<>("Không tìm thấy id");
            }

            long cancleAt = Timestamp.getNowTimeStamp();

            int updated = this.calendarRepository.setCancleAt(id, cancleAt);

            if (updated == -1) {
                return new Response<>("Lỗi truy vấn dữ liệu");
            }

            if (updated == 0) {
                return new Response<>("Không thể huỷ");
            }

            return new Response<Boolean>().ok(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

}
