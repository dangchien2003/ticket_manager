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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
            return new Response<Void>("Lỗi không xác định");
        }
    }

    public Response<List<Staff>> getAllStaff() {
        try {
            ResultSet result = this.staffRepository.getAll();
            if (result == null) {
                return new Response<>("Lỗi truy vấn");
            }

            List<Staff> allStaff = new ArrayList<>();

            while (result.next()) {
                Staff staff = new Staff();
                staff.setIdnv(result.getString("idnv"));
                staff.setName(result.getString("name"));
                staff.setSdt(result.getString("sdt"));
                staff.setEmail(result.getString("email"));
                staff.setSex(result.getInt("sex"));
                staff.setRank(result.getString("rank"));
                staff.setBlockAt(result.getLong("blockAt"));
                if(staff.getBlockAt() > 0){
                    staff.setStringBlockAt(Timestamp.convertTimeStampToString(staff.getBlockAt(), "HH:mm:ss dd-MM-yyyy"));
                }
                allStaff.add(staff);
            }
            return new Response<List<Staff>>().ok(allStaff);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Lỗi không xác định");
        }
    }

    public Response<List<Staff>> findStaff(String id, String name) {
        try {
            if (id.trim().length() == 0 && name.trim().length() == 0) {
                return new Response<>("Không tìm thấy thông tin lọc");
            }

            ResultSet resultSet = null;
            if (id.trim().length() > 0 && name.trim().length() > 0) {
                resultSet = this.staffRepository.find(id, name);
            } else if (id.trim().length() > 0) {
                resultSet = this.staffRepository.findById(id);

            } else {
                resultSet = this.staffRepository.findByName(name);

            }
            if (resultSet == null) {
                return new Response<>("Lỗi truy vấn");
            }
            List<Staff> listStaff = new ArrayList<>();

            while (resultSet.next()) {
                Staff staff = new Staff();
                staff.setIdnv(resultSet.getString("idnv"));
                staff.setName(resultSet.getString("name"));
                staff.setSdt(resultSet.getString("sdt"));
                staff.setEmail(resultSet.getString("email"));
                staff.setSex(resultSet.getInt("sex"));
                staff.setRank(resultSet.getString("rank"));
                staff.setBlockAt(resultSet.getLong("blockAt"));
                if(staff.getBlockAt() > 0){
                    staff.setStringBlockAt(Timestamp.convertTimeStampToString(staff.getBlockAt(), "HH:mm:ss dd-MM-yyyy"));
                }
                listStaff.add(staff);
            }
            return new Response<List<Staff>>().ok(listStaff);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Lỗi không xác định");
        }
    }

    public Response<List<Object>> blockStaff(String id) {
        try {
            if (id.equals("")) {
                return new Response<>("Id không tồn tại");
            }
            long time = Timestamp.getNowTimeStamp();
            int updated = this.staffRepository.setBlockAt(id, time);
            if (updated == -1) {
                return new Response<>("Lỗi truy vấn");

            }
            if (updated < 1) {
                return new Response<>("Cập nhật thất bại");
            }
            return new Response<List<Object>>().ok(new ArrayList<Object>(Arrays.asList(Timestamp.convertTimeStampToString(time, "HH:mm:ss dd-MM-yyyy"),time)));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Lỗi không xác định");
        }
    }

    public Response<String> repassword(String id) {
        try {
            if (id.equals("")) {
                return new Response<>("Id không tồn tại");
            }

            String passwordStr = StringUtil.genString(10);
            String passwordHashed = Hash.getHash(passwordStr, null);

            int updated = this.staffRepository.updatePassword(id, passwordHashed);
            if (updated == -1) {
                return new Response<>("Lỗi truy vấn");
            }

            if (updated < 1) {
                return new Response<>("Thay đổi mật khẩu thất bại");
            }

            return new Response<String>().ok(passwordStr);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Lỗi không xác định");
        }

    }

    public Response<Void> editStaff(Staff staff) {
        try {
            if(staff.getIdnv().equals("")){
                return new Response<>("Id không xác định");
            }
            if (staff.getName().trim().length() != staff.getName().length() || staff.getEmail().trim().length() != staff.getEmail().length() || staff.getSdt().trim().length() != staff.getSdt().length()) {
                return new Response<>("Dữ liệu không chứa khoảng trắng đầu cuối");
            }

            if (staff.getName().equals("")) {
                return new Response<>("Tên không hợp lệ");
            }
            if (staff.getSdt().equals("")) {
                return new Response<>("Sđt không hợp lệ");
            }
            
            if (!EmailValid.isEmail(staff.getEmail())) {
                return new Response<>("Email không hợp lệ");
            }

            if (staff.getSex() == -1) {
                return new Response<>("Giới tính không hợp lệ");
            }

            if (staff.getSdt().equals("")) {
                return new Response<>("Sđt không hợp lệ");
            }
            
            int updated = this.staffRepository.updateStaff(staff);
            if (updated == -1) {
                return new Response<>("Lỗi truy vấn");
            }

            if (updated < 1) {
                return new Response<>("Thay đổi mật khẩu thất bại");
            }

            return new Response<Void>().ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Lỗi không xác định");
        }
    }
}
