/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.controller;

import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.model.Staff;
import com.mycompany.ticket_manager.service.StaffService;

/**
 *
 * @author chien
 */
public class StaffController {

    StaffService staffService;

    public StaffController() {
        this.staffService = new StaffService();
    }

    public Response<?> login(String email, String password, String permissions) {
        return this.staffService.login(email, password, permissions);
    }

    public Response<String> randomPassword() {
        return this.staffService.randomPassword();
    }
    
    public Response<?> addStaff(Staff staff){
        return this.staffService.addStaff(staff);
    }

}
