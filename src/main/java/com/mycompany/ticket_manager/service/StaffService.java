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

            return new Response<CurrentStaff>(true, "Email không đúng", new CurrentStaff(rank, rank, rank));
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
            if (staff == null) {
                return new Response<Void>("Dữ liệu không tồn tại");
            }

            if (staff.getName().trim().length() != staff.getName().length() || staff.getEmail().trim().length() != staff.getEmail().length() || staff.getSdt().trim().length() != staff.getSdt().length()) {
                return new Response<Void>("Dữ liệu không chứa khoảng trắng đầu cuối");
            }

            if (staff.getName().equals("")) {
                return new Response<Void>("Tên không hợp lệ");
            }
            if (staff.getSdt().equals("")) {
                return new Response<Void>("Sđt không hợp lệ");
            }

            if (staff.getPassword().length() < 8) {
                return new Response<Void>("Mật khẩu quá ngắn");
            }

            if (!EmailValid.isEmail(staff.getEmail())) {
                return new Response<Void>("Email không hợp lệ");
            }

            if (staff.getSex() == -1) {
                return new Response<Void>("Giới tính không hợp lệ");
            }

            if (staff.getSdt().equals("")) {
                return new Response<Void>("Sđt không hợp lệ");
            }

            ResultSet getStaff = staffRepository.getStaffByEmail(staff.getEmail());
            getStaff.next();
            if (getStaff.getRow() == 1) {
                return new Response<Void>("Email đã tồn tại");
            }

            staff.setIdnv("STAFF_" + Timestamp.getNowTimeStamp() + "_" + NumberUtil.genNumber(3));
            staff.setRank("staff");
            staff.setPassword(Hash.getHash(staff.getPassword(), null));

            int inserted = staffRepository.addStaff(staff);

            if (inserted < 1) {
                return new Response<Void>("Thêm không thành công");
            }
            return new Response<Void>().ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<Void>("Lỗi truy vấn");
        }
    }
}
