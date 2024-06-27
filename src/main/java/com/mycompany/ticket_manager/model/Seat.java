/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.model;

/**
 *
 * @author chien
 */
public class Seat {

    private long id;
    private String idTicket;
    private String location;
    private String idCalendar;

    public Seat() {
    }

    public Seat(String idTicket, String location, String idCalendar) {
        this.idTicket = idTicket;
        this.location = location;
        this.idCalendar = idCalendar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIdCalendar() {
        return idCalendar;
    }

    public void setIdCalendar(String idCalendar) {
        this.idCalendar = idCalendar;
    }

}
