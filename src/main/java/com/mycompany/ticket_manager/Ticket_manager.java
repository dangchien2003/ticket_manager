/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ticket_manager;

import com.mycompany.ticket_manager.util.ConnectDatabase;

/**
 *
 * @author chien
 */
public class Ticket_manager {

    public static void main(String[] args) {
        if (ConnectDatabase.getConnecttion() == null) {
            System.out.println("Kết nối thất bại");
        }
        else{
            System.out.println("Kết nối thành công"); 
        }
    }
}
