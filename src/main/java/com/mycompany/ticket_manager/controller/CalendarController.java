/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.controller;

import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.service.CalendarService;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chien
 */
public class CalendarController {
    CalendarService calendarService;
    public CalendarController(){
        this.calendarService = new CalendarService();
    }
    
    public Response<List<Map<String, String>>> getCalendarMovie(String idMovie, long timestamp){
        return this.calendarService.getCalendarMovie(idMovie, timestamp);
    }
    
    public Response<String> getRemaingTime(String idCalendar){
        return this.calendarService.getRemaingTime(idCalendar);
    }
}
