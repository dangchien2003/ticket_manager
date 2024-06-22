/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.service;

import com.mycompany.ticket_manager.model.CurrentStaff;
import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.model.Staff;
import com.mycompany.ticket_manager.repository.StaffRepository;
import com.mycompany.ticket_manager.util.Hash;
import com.mycompany.ticket_manager.util.NumberUtil;
import com.mycompany.ticket_manager.util.StringUtil;
import com.mycompany.ticket_manager.util.Timestamp;
import com.mycompany.ticket_manager.util.valid.EmailValid;
import com.mysql.cj.util.TimeUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                return new Response<Void>("Email không đúng");
            }

            if (password.length() == 0) {
                return new Response<Void>("Mật khẩu không hợp lệ");
            }

            ResultSet result = staffRepository.getStaffByEmail(email);

            if (result == null) {
                return new Response<Void>("Lỗi truy vấn");
            }

            result.next();
            if (result.getRow() == 0) {
                return new Response<Void>("Email không tồn tại");
            }

            String DbPassword = result.getString("password");
            if (!Hash.verify(DbPassword, password)) {
                return new Response<Void>("Mật khẩu không đúng");
            }

            long blockAt = result.getLong("blockAt");
            if (blockAt != 0) {
                return new Response<Void>("Tài khoản đã bị khoá");
            }

            String rank = result.getString("rank");
            if (!rank.equals(permissions)) {
                return new Response<Void>("Không có quyền truy cập");
            }

            String id = result.getString("idnv");
            String name = result.getString("name");

            return new Response<CurrentStaff>().ok(new CurrentStaff(id, name, rank));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<Void>(e.getMessage());
        }
    }

    public Response<String> randomPassword() {
        return new Response<String>().ok(StringUtil.genString(10));
    }

    public Response<?> addStaff(Staff staff) {
        try {
            staff.setName(staff.getName().trim());
            staff.setSdt(staff.getSdt().trim());
            staff.setEmail(staff.getEmail().trim());

            if (staff.getName().equals("")) {
                return new Response<Void>().error("Tên không hợp lệ");
            }

            if (staff.getSdt().equals("")) {
                return new Response<Void>("Sđt không hợp lệ");
            }

            if (staff.getSex() == -1) {
                return new Response<Void>("Giới tính không hợp lệ");
            }

            if (EmailValid.isEmail(staff.getEmail()) == false) {
                return new Response<Void>("Email không hợp lệ");
            }

            if (staff.getPassword().trim().length() < 8) {
                return new Response<Void>("Mật khẩu không hợp lệ");
            }

            ResultSet getStaff = staffRepository.getStaffByEmail(staff.getEmail());
            getStaff.next();
            if (getStaff.getRow() == 1) {
                return new Response<Void>("Email đã tồn tại");
            }

            staff.setIdnv("STAFF_" + Timestamp.getNowTimeStamp() + "_" + NumberUtil.genNumber(3));
            staff.setPassword(Hash.getHash(staff.getPassword(), null));
            staff.setRank("staff");
            int inserted = staffRepository.addStaff(staff);

            if (inserted < 1) {
                return new Response<Void>("Lỗi thêm nhân viên");
            }
            return new Response<Void>().ok(null);
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response<Void>("Lỗi server");

        }
    }
}
