/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.service;

import com.mycompany.ticket_manager.model.CurrentStaff;
import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.model.Seat;
import com.mycompany.ticket_manager.model.Ticket;
import com.mycompany.ticket_manager.repository.CalendarRepository;
import com.mycompany.ticket_manager.repository.MovieRepository;
import com.mycompany.ticket_manager.repository.SeatRepository;
import com.mycompany.ticket_manager.repository.StaffRepository;
import com.mycompany.ticket_manager.repository.TicketRepository;
import com.mycompany.ticket_manager.util.NumberUtil;
import com.mycompany.ticket_manager.util.Timestamp;
import com.mycompany.ticket_manager.util.valid.EmailValid;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chien
 */
public class TicketService {

    Dotenv dotenv = Dotenv.load();
    MovieRepository movieRepository;
    CalendarRepository calendarRepository;
    StaffRepository staffRepository;
    TicketRepository ticketRepository;
    SeatRepository seatRepository;

    public TicketService() {
        this.movieRepository = new MovieRepository();
        this.calendarRepository = new CalendarRepository();
        this.staffRepository = new StaffRepository();
        this.ticketRepository = new TicketRepository();
        this.seatRepository = new SeatRepository();
    }

    public Response<Map<String, Object>> getPriceService(int quantity, int object) {
        try {
            if (quantity < 0 || quantity > 100) {
                return new Response<>("Sai số lượng");
            }
            String name;
            if (object == 1) {
                name = "PRICE_POPCORN";
            } else if (object == 2) {
                name = "PRICE_WATER";
            } else {
                throw new Exception("Đối tượng không xác định");
            }
            String priceString = dotenv.get(name);
            if (priceString == null || priceString.trim().equals("")) {
                System.out.println("Không có nội dung " + name);
                return new Response<>("Lỗi xử lý");
            }
            int price = 0;
            try {
                price = Integer.parseInt(priceString);
                if (price <= 0) {
                    throw new Exception("Giá không hợp lệ");
                }
            } catch (Exception e) {
                System.out.println("Lỗi chuyển chuỗi");
                e.printStackTrace();
                return new Response<>("Lỗi xử lý");
            }
            int total = price * quantity;
            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("num", total);
                    put("str", NumberUtil.convertPrice(total));
                }
            };
            return new Response<Map<String, Object>>().ok(data);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<Map<String, Object>> getPriceChairs(List<String> listChair, String movie) {
        try {

            ResultSet resultSet = this.movieRepository.findById(movie);
            if (resultSet == null) {
                return new Response<>("Lỗi truy vấn dữ liệu");
            }

            resultSet.next();

            if (resultSet.getRow() == 0) {
                return new Response<>("Không có dữ liệu");
            }

            int price = resultSet.getInt("minPrice");
            int total = price * listChair.size();
            Map<String, Object> data = new HashMap<String, Object>() {
                {
                    put("num", total);
                    put("str", NumberUtil.convertPrice(total));
                }
            };
            return new Response<Map<String, Object>>().ok(data);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<Ticket> checkInfo(Ticket ticket, List<String> chairs) {
        try {
            ticket.setName(ticket.getName().trim());
            ticket.setEmail(ticket.getEmail().trim());
            if (ticket.getName().equals("")) {
                return new Response<>("Trống tên khách hàng");
            }
            if (ticket.getEmail().equals("")) {
                return new Response<>("Trống email");
            }

            if (!EmailValid.isEmail(ticket.getEmail())) {
                return new Response<>("Email không đúng");
            }

            if (chairs.isEmpty()) {
                return new Response<>("Không có ghế nào được chọn");
            }

            ResultSet resultSet = this.calendarRepository.getCalendar(ticket.getCalendar());
            if (resultSet == null) {
                return new Response<>("Lỗi truy vấn dữ liệu");
            }

            resultSet.next();

            if (resultSet.getRow() == 0) {
                return new Response<>("Lịch chiếu không tồn tại");
            }
            resultSet = this.staffRepository.getStaffById(ticket.getCreateBy());
            if (resultSet == null) {
                return new Response<>("Lỗi truy vấn dữ liệu");
            }

            resultSet.next();

            if (resultSet.getRow() == 0) {
                return new Response<>("Nhân viên không xác định");
            }

            String id = "TICKET_" + Timestamp.getNowTimeStamp() + "_" + NumberUtil.genNumber(3);
            ticket.setId(id);
            return new Response<Ticket>().ok(ticket);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

    public Response<Boolean> addTicket(Ticket ticket, List<String> chairs) {
        try {
            ticket.setCreateAt(Timestamp.getNowTimeStamp());
            int insertTicket = this.ticketRepository.insertTicket(ticket);
            if (insertTicket == -1) {
                return new Response<>("Lỗi try vấn dữ liệu");
            }
            if (insertTicket == 0) {
                return new Response<>("Tạo vé thất bại");
            }
            
            List<Seat> dataSeat = new ArrayList<>();
            
            for(String location: chairs){
                dataSeat.add(new Seat(ticket.getId(), location, ticket.getCalendar()));
            }
            
            int inserted = this.seatRepository.insertListSeat(dataSeat);
            
            if(inserted == -1){
                return new Response<>("Lỗi truy vấn dữ liệu");
            }

            return new Response<Boolean>().ok(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>("Có lỗi xảy ra");
        }
    }

}
