/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.model;

/**
 *
 * @author chien
 */
public class Ticket {
    private String id;
    private String name;
    private String email;
    private String calendar;
    private int numPerson;
    private int numPopcorn;
    private int numWater;
    private int priceTicket;
    private int pricePopcorn;
    private int priceWater;
    private String createBy;
    private long createAt;
    private long checkinAt;
    private String checkinBy;

    public Ticket() {
    }

    public Ticket(String name, String email, String calendar, int numPerson, int numPopcorn, int numWater) {
        this.name = name;
        this.email = email;
        this.calendar = calendar;
        this.numPerson = numPerson;
        this.numPopcorn = numPopcorn;
        this.numWater = numWater;
        
    }

    public Ticket(String name, String email, String calendar, int numPerson, int numPopcorn, int numWater, int priceTicket, int pricePopcorn, int priceWater, String createBy) {
        this.name = name;
        this.email = email;
        this.calendar = calendar;
        this.numPerson = numPerson;
        this.numPopcorn = numPopcorn;
        this.numWater = numWater;
        this.priceTicket = priceTicket;
        this.pricePopcorn = pricePopcorn;
        this.priceWater = priceWater;
        this.createBy = createBy;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public int getNumPerson() {
        return numPerson;
    }

    public void setNumPerson(int numPerson) {
        this.numPerson = numPerson;
    }

    public int getNumPopcorn() {
        return numPopcorn;
    }

    public void setNumPopcorn(int numPopcorn) {
        this.numPopcorn = numPopcorn;
    }

    public int getNumWater() {
        return numWater;
    }

    public void setNumWater(int numWater) {
        this.numWater = numWater;
    }

    public int getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(int priceTicket) {
        this.priceTicket = priceTicket;
    }

    public int getPricePopcorn() {
        return pricePopcorn;
    }

    public void setPricePopcorn(int pricePopcorn) {
        this.pricePopcorn = pricePopcorn;
    }

    public int getPriceWater() {
        return priceWater;
    }

    public void setPriceWater(int priceWater) {
        this.priceWater = priceWater;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public long getCheckinAt() {
        return checkinAt;
    }

    public void setCheckinAt(long checkinAt) {
        this.checkinAt = checkinAt;
    }

    public String getCheckinBy() {
        return checkinBy;
    }

    public void setCheckinBy(String checkinBy) {
        this.checkinBy = checkinBy;
    }
    
    
    
}
