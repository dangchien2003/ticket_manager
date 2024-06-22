/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ticket_manager;

<<<<<<< Updated upstream
import com.mycompany.ticket_manager.util.ConnectDatabase;
=======
import com.mycompany.ticket_manager.view.FrameLogin;
>>>>>>> Stashed changes

/**
 *
 * @author chien
 */
public class Ticket_manager {

    public static void main(String[] args) {
<<<<<<< Updated upstream
        if (ConnectDatabase.getConnecttion() == null) {
            System.out.println("Kết nối thất bại");
        }
        else{
            System.out.println("Kết nối thành công"); 
        }
=======
        FrameLogin login = new FrameLogin();
        login.setVisible(true); 

>>>>>>> Stashed changes
    }
}
