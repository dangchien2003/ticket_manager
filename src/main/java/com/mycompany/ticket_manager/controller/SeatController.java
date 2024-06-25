/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.controller;

import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.service.SeatService;
import java.util.List;

/**
 *
 * @author chien
 */
public class SeatController {
    SeatService seatService;
    public SeatController(){
        this.seatService = new SeatService();
    }
    
    public Response<List<String>> getAllChairBoughtOfCalendar(String idCalendar){
        return this.seatService.getAllChairBoughtOfCalendar(idCalendar);
    }
}
