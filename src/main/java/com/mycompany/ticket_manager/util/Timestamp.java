/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
/**
 *
 * @author chien
 */
public class Timestamp {

    public static long getNowTimeStamp() {
        Date date = new Date();
        return date.getTime() / 1000;
    }

    public static String convertTimeStampToString(long timestamp) {
        return convertTimeStamp(timestamp * 1000, "dd-MM-yyyy HH:mm:ss");
    }

    public static String convertTimeStampToString(long timestamp, String format) {
        return convertTimeStamp(timestamp * 1000, format);

    }

    private static String convertTimeStamp(long timestamp, String format) {
        SimpleDateFormat sdf;
        try {
            sdf = new SimpleDateFormat(format);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        Date date = new Date(timestamp);
        return sdf.format(date);
    }

    public static long convertTimeToTimestamp(String time, String format) {
       try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

            if (format.contains("HH")) {
                // Nếu định dạng chứa thời gian, sử dụng LocalDateTime
                LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
                return dateTime.toEpochSecond(ZoneOffset.UTC);
            } else {
                // Nếu định dạng chỉ chứa ngày tháng, sử dụng LocalDate và atStartOfDay()
                LocalDateTime dateTime = LocalDateTime.parse(time + " 00:00:00", DateTimeFormatter.ofPattern(format + " HH:mm:ss"));
                return dateTime.toEpochSecond(ZoneOffset.UTC);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Định dạng không hỗ trợ: " + e.getMessage());
            return -1;
        }
    }

}
