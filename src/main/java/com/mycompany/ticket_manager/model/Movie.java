/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.model;

/**
 *
 * @author chien
 */
public class Movie {
    private String id;
    private String name;
    private int age;
    private int minPrice;
    private int time;
    private long hideAt;
    private String strHideAt;
    private long createAt;
    private String strCreateAt;
    private String image;

    public Movie() {
    }

    public Movie(String name, int age, int minPrice, int time) {
        this.name = name;
        this.age = age;
        this.minPrice = minPrice;
        this.time = time;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getHideAt() {
        return hideAt;
    }

    public void setHideAt(long hideAt) {
        this.hideAt = hideAt;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStrHideAt() {
        return strHideAt;
    }

    public void setStrHideAt(String strHideAt) {
        this.strHideAt = strHideAt;
    }

    public String getStrCreateAt() {
        return strCreateAt;
    }

    public void setStrCreateAt(String strCreateAt) {
        this.strCreateAt = strCreateAt;
    }
    
    
    

}
