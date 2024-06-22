/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.util;

import java.util.Random;
import java.text.NumberFormat;
import java.util.Locale;
/**
 *
 * @author chien
 */
public class NumberUtil {
    public static long genNumber(int length) {
        String result = "";
        Random random = new Random();
        return random.nextLong((long) Math.pow(10, length));
    }
    
    public static String convertPrice(int price){
         try {
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

            return currencyFormatter.format(price);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
