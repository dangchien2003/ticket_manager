/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.controller;

import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.model.Ticket;
import com.mycompany.ticket_manager.service.TicketService;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chien
 */
public class TicketController {

    TicketService ticketService;

    public TicketController() {
        this.ticketService = new TicketService();
    }

    public Response<Map<String, Object>> getPricePopcorn(int quantity) {
        return this.ticketService.getPriceService(quantity, 1);
    }

    public Response<Map<String, Object>> getPriceWater(int quantity) {
        return this.ticketService.getPriceService(quantity, 2);
    }

    public Response<Map<String, Object>> getPriceChairs(List<String> listChair, String movie) {
        return this.ticketService.getPriceChairs(listChair, movie);
    }
    
    public Response<Ticket> checkInfo(Ticket ticket, List<String> chairs){
        return this.ticketService.checkInfo(ticket, chairs);
    }
    
    public Response<Boolean> addTicket(Ticket ticket, List<String> chairs, String nameMovie, String playAt){
        return this.ticketService.addTicket(ticket, chairs, nameMovie, playAt);
    }
    
    public Response<Map<String, String>> getInfoTicket(String id) {
        return this.ticketService.getInfoTicket(id);
    }
    public Response<Boolean> checkIn(String id, String staff) {
        return this.ticketService.checkIn(id, staff);
    }
}
