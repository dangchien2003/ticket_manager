/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.service;

import com.mycompany.ticket_manager.model.CurrentStaff;
import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.repository.StaffRepository;
import com.mycompany.ticket_manager.util.Hash;
import com.mycompany.ticket_manager.util.valid.EmailValid;
import java.sql.ResultSet;

/**
 *
 * @author chien
 */
public class StaffService {

    StaffRepository staffRepository;

    public StaffService() {
        this.staffRepository = new StaffRepository();
    }

    public Response<?> login(String email, String password, String permissions) {
        try {
            email = email.trim();
            password = password.trim();
            if (EmailValid.isEmail(email) == false) {
                return new Response<Void>(false, "Email không đúng");
            }

            if (password.length() == 0) {
                return new Response<Void>(false, "Mật khẩu không hợp lệ");
            }

            ResultSet result = staffRepository.getStaffByEmail(email);
            result.next();
            if (result.getRow() == 0) {
                return new Response<Void>(false, "Email không tồn tại");
            }


            String DbPassword = result.getString("password");
            if (!Hash.verify(DbPassword, password)) {
                return new Response<Void>(false, "Mật khẩu không đúng");
            }

            long blockAt = result.getLong("blockAt");
            if (blockAt != 0) {
                return new Response<Void>(false, "Tài khoản đã bị khoá");
            }

            String rank = result.getString("rank");
            if (!rank.equals(permissions)) {
                return new Response<Void>(false, "Không có quyền truy cập");
            }
            
            String id = result.getString("idnv");
            String name = result.getString("name");

            return new Response<CurrentStaff>(true, "Email không đúng", new CurrentStaff(rank, rank, rank));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<Void>(false, e.getMessage());
        }
    }
}
