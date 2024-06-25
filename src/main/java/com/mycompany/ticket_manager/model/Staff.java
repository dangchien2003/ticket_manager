/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.model;

/**
 *
 * @author chien
 */
public class Staff {

    private String idnv;
    private String name;
    private String sdt;
    private String email;
    private int sex;
    private String rank;
    private Long blockAt;
    private String stringBlockAt;
    private String password;

    public Staff() {
    }

    public Staff(String idnv, String name, String sdt, String email, int sex, String rank, Long blockAt, String password) {
        this.idnv = idnv;
        this.name = name;
        this.sdt = sdt;
        this.email = email;
        this.sex = sex;
        this.rank = rank;
        this.blockAt = blockAt;
        this.password = password;
    }

    public String getIdnv() {
        return idnv;
    }

    public void setIdnv(String idnv) {
        this.idnv = idnv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Long getBlockAt() {
        return blockAt;
    }

    public void setBlockAt(Long blockAt) {
        this.blockAt = blockAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStringBlockAt() {
        return stringBlockAt;
    }

    public void setStringBlockAt(String stringBlockAt) {
        this.stringBlockAt = stringBlockAt;
    }
    
    
    
    
}
