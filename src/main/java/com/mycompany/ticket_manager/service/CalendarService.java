/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.service;

import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.repository.CalendarRepository;
import com.mycompany.ticket_manager.util.Timestamp;
import java.sql.ResultSet;
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

    public CalendarService() {
        this.calendarRepository = new CalendarRepository();
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

            if (remaining < timeMovie*60 && remaining >= 0) {
                return new Response<String>().ok("P" + room + " " + remaining / 60 + "/" + timeMovie + " Phút");
            }else {
                return new Response<String>().ok("P" + room);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }
}
