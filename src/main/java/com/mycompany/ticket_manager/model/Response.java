/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.model;

/**
 *
 * @author chien
 */
public class Response<T> {

    private boolean success;
    private String message;
    private T data = null;

    public Response() {
    }
    
    public Response<T> ok(T data){
        this.success = true;
        this.data = data;
        return this;
    }
    
    public Response<T> error(String message){
        this.success = false;
        this.message = message;
        return this;
    }

    public Response(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Response(String message) {
        this.success = false;
        this.message = message;
    }
//
//    public Response(T data) {
//        this.success = true;
//        this.message = message;
//    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
