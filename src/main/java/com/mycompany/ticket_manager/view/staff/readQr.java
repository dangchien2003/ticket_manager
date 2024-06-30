/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ticket_manager.view.staff;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.mycompany.ticket_manager.controller.TicketController;
import com.mycompany.ticket_manager.model.CurrentStaff;
import com.mycompany.ticket_manager.model.Response;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.xml.sax.Attributes;

/**
 *
 * @author chien
 */
public class readQr extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private volatile boolean running = true;
    private String idTicket;
    private TicketController ticketController;
    private CurrentStaff currentStaff;

    private void initWebcam() {
        Dimension size = WebcamResolution.QQVGA.getSize();
        webcam = Webcam.getWebcams().get(4);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 350));
        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
//                Logger.getLogger(readQr.class.getName()).log(Level.SEVERE, null, ex);
                }
                Result result = null;
                BufferedImage image = null;

                if (!running) { // Nếu running là false, ngừng chạy luồng
                    continue;
                }

                if (webcam.isOpen()) {
                    if ((image = webcam.getImage()) == null) {
                        continue;
                    }
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException ex) {
//                Logger.getLogger(readQr.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (result != null) {
                    String content = result.getText();
                    getInfoTicket(content);
                    running = false;

                }
            } catch (Exception e) {

            }

        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    private void getInfoTicket(String id) {
        this.idTicket = id;
        Response<Map<String, String>> response = this.ticketController.getInfoTicket(id);
        if (response.getSuccess() == false) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Thông báo lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Map<String, String> data = response.getData();
        this.id.setText(data.get("id"));
        this.time.setText(data.get("time"));
        this.room.setText(data.get("room"));
        this.person.setText(data.get("person"));
        this.location.setText(data.get("location"));
        this.popcorn.setText(data.get("popcorn"));
        this.water.setText(data.get("water"));
    }

    private void checkinTicket() {
        Response<Boolean> response = this.ticketController.checkIn(idTicket, currentStaff.getId());
        if (response.getSuccess() == false) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Thông báo lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Checkin thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        clear();
    }

    private void clear() {
        id.setText("");
        time.setText("");
        room.setText("");
        person.setText("");
        location.setText("");
        popcorn.setText("");
        water.setText("");
        running = true;
        idTicket = "";
    }

    /**
     * Creates new form readQr
     */
    public readQr(CurrentStaff staff) {
//        
        initComponents();
        initWebcam();
        this.ticketController = new TicketController();
        this.currentStaff = staff;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        close = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        checkin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        room = new javax.swing.JLabel();
        person = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        location = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        popcorn = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        water = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        close.setBackground(new java.awt.Color(61, 213, 243));
        close.setText("Bỏ qua");
        close.setPreferredSize(new java.awt.Dimension(72, 28));
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 350));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkin.setBackground(new java.awt.Color(32, 201, 151));
        checkin.setText("Vào xem");
        checkin.setPreferredSize(new java.awt.Dimension(78, 28));
        checkin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clickCheckin(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("Mã vé:");

        id.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Giờ chiếu:");

        time.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText(" Phòng:");

        room.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        person.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText(" Số người:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Vị trí ngồi:");

        location.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setText("Bỏng ngô:");

        popcorn.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel13.setText("Nước ngọt:");

        water.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(checkin, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(room, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(person, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(location, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(popcorn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(water, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(id))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(time))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(room))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(person))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(location))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(popcorn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(water))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_closeActionPerformed

    private void clickCheckin(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickCheckin
        // TODO add your handling code here:
        checkinTicket();
    }//GEN-LAST:event_clickCheckin

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(readQr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(readQr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(readQr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(readQr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new readQr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkin;
    private javax.swing.JButton close;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel location;
    private javax.swing.JLabel person;
    private javax.swing.JLabel popcorn;
    private javax.swing.JLabel room;
    private javax.swing.JLabel time;
    private javax.swing.JLabel water;
    // End of variables declaration//GEN-END:variables
}
