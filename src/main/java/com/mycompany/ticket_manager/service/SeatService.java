/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.service;

import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.repository.SeatRepository;
import com.mycompany.ticket_manager.util.Timestamp;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chien
 */
public class SeatService {

    SeatRepository seatRepository;

    public SeatService() {
        this.seatRepository = new SeatRepository();
    }

    public Response<List<String>> getAllChairBoughtOfCalendar(String idCalendar) {
        try {
            ResultSet resultSet = this.seatRepository.getChairsBought(idCalendar);
            if (resultSet == null) {
                return new Response<>("Lỗi truy vấn");
            }
            List<String> listChairs = new ArrayList<>();

            while (resultSet.next()) {
               listChairs.add(resultSet.getString("location"));
            }
            return new Response<List<String>>().ok(listChairs);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }
}
