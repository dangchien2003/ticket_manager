/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.controller;

import com.mycompany.ticket_manager.model.CurrentStaff;
import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.model.Staff;
import com.mycompany.ticket_manager.service.StaffService;
import java.util.List;

/**
 *
 * @author chien
 */
public class StaffController {

    StaffService staffService;

    public StaffController() {
        this.staffService = new StaffService();
    }

    public Response<CurrentStaff> login(String email, String password, String permissions) {
        return this.staffService.login(email, password, permissions);
    }

    public Response<String> randomPassword() {
        return this.staffService.randomPassword();
    }

    public Response<?> addStaff(Staff staff) {
        return this.staffService.addStaff(staff);
    }

    public Response<List<Staff>> getAllStaff() {
        return this.staffService.getAllStaff();
    }

    public Response<List<Staff>> findStaff(String id, String name) {
        return this.staffService.findStaff(id, name);
    }

    public Response<List<Object>> blockStaff(String id) {
        return this.staffService.blockStaff(id);

    }

    public Response<String> repassword(String id) {
        return this.staffService.repassword(id);
    }

    public Response<?> editStaff(Staff staff) {
        return this.staffService.editStaff(staff);
    }

}
