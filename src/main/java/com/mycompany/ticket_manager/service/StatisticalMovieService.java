/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.service;

import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.repository.StatisticalRepository;
import com.mycompany.ticket_manager.util.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chien
 */
public class StatisticalMovieService {

    StatisticalRepository statisticalRepository;

    public StatisticalMovieService() {
        this.statisticalRepository = new StatisticalRepository();
    }

    public Response<List<Map<String, Object>>> movie(int style, String movie, String day, String month, String year) {
        try {
            long timeStart = 0;
            long timeEnd = 0;

            if (style < 1 || style > 3) {
                return new Response<>("Thống kê chưa hỗ trợ");
            }

            if (style == 1) {
                timeStart = Timestamp.convertTimeToTimestamp(day + "-" + month + "-" + year, "dd-MM-yyyy");
                timeEnd = timeStart + 86400;
            } else if (style == 2) {
                timeStart = Timestamp.convertTimeToTimestamp("01-" + month + "-" + year, "dd-MM-yyyy");
                YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));
                int daysInMonth = yearMonthObject.lengthOfMonth();
                timeEnd = timeStart + 86400 * daysInMonth;
            } else {
                timeStart = Timestamp.convertTimeToTimestamp("01-01-" + year, "dd-MM-yyyy");
                Year yearObject = Year.of(Integer.parseInt(year));
                int daysInYear = yearObject.length();
                timeEnd = timeStart + 86400 * daysInYear;
            }

            if (timeStart == -1) {
                return new Response<>("Chuyển thời gian lỗi");
            }

            ResultSet resultSet = this.statisticalRepository.movie(timeStart, timeEnd, movie);
            List<Map<String, Object>> listData = new ArrayList<>();

            switch (style) {
                case 1:
                    listData = this.followDay(resultSet);
                    break;
                case 2:
                    listData = this.followMonth(resultSet);
                    break;
                default:
                    listData = this.followYear(resultSet);
                    break;
            }

            if (listData.isEmpty()) {
                return new Response<>("Không có dữ liệu");
            }
            return new Response<List<Map<String, Object>>>().ok(listData);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    private List<Map<String, Object>> followDay(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> listData = new ArrayList<>();
        int ticket = 0;
        int count = 0;
        String date = "";
        while (resultSet.next()) {
            if (resultSet.getRow() == 1) {
                date = Timestamp.convertTimeStampToString(resultSet.getLong("createAt"), "dd-MM-yyyy");
            }
            ticket += resultSet.getInt("priceTicket");
            count++;
        }

        Map<String, Object> row = new HashMap<>();
        row.put("date", date);
        row.put("ticket", ticket);
        row.put("numTicket", count);
        listData.add(row);
        return listData;
    }

    private List<Map<String, Object>> followMonth(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> listData = new ArrayList<>();
        int ticket = 0;
        int count = 0;
        String date = "";
        while (resultSet.next()) {
            String dayItem = Timestamp.convertTimeStampToString(resultSet.getLong("createAt"), "dd-MM-yyyy");
            if (date.equals("")) {
                date = dayItem;
            }
            if (date.equals(dayItem)) {
                ticket += resultSet.getInt("priceTicket");
                count++;
            } else {
                Map<String, Object> row = new HashMap<>();
                row.put("date", date);
                row.put("ticket", ticket);
                row.put("numTicket", count);
                listData.add(row);
                date = dayItem;
                ticket = resultSet.getInt("priceTicket");
                count = 1;
            }
        }
        Map<String, Object> row = new HashMap<>();
        row.put("date", date);
        row.put("ticket", ticket);
        row.put("numTicket", count);
        listData.add(row);
        return listData;
    }

    private List<Map<String, Object>> followYear(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> listData = new ArrayList<>();
        int ticket = 0;
        int count = 0;
        String date = "";
        while (resultSet.next()) {
            String dayItem = Timestamp.convertTimeStampToString(resultSet.getLong("createAt"), "MM-yyyy");
            if (date.equals("")) {
                date = dayItem;
            }
            if (date.equals(dayItem)) {
                ticket += resultSet.getInt("priceTicket");
                count++;
            } else {
                Map<String, Object> row = new HashMap<>();
                row.put("date", date);
                row.put("ticket", ticket);
                row.put("numTicket", count);

                listData.add(row);
                date = dayItem;
                ticket = resultSet.getInt("priceTicket");
                count = 1;
            }
        }
        Map<String, Object> row = new HashMap<>();
        row.put("date", date);
        row.put("ticket", ticket);
        row.put("numTicket", count);
        listData.add(row);
        return listData;
    }
}
