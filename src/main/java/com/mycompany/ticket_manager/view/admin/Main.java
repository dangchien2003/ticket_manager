/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ticket_manager.view.admin;

import java.awt.BorderLayout;
import java.awt.Component;
import com.mycompany.ticket_manager.view.admin.FrameStatisticalRevenue;

/**
 *
 * @author chien
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        allMovie = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        addMovie = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        revenue = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        buyTicket = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        movie = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel1.setText("Xin chào");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(439, 439, 439)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(438, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(jLabel1)
                .addContainerGap(245, Short.MAX_VALUE))
        );

        jMenu1.setText("Nhân viên");

        jMenuItem1.setText("Tất cả nhân viên");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clickShowAllStaff(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem2.setText("Thêm nhân viên");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clickAddStaff(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Phim");

        allMovie.setText("Tất cả phim");
        allMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allMovieActionPerformed(evt);
            }
        });
        jMenu2.add(allMovie);
        jMenu2.add(jSeparator2);

        addMovie.setText("Thêm phim");
        jMenu2.add(addMovie);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Lịch chiếu");

        jMenuItem3.setText("Tất cả lịch");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clickAllCalendar(evt);
            }
        });
        jMenu3.add(jMenuItem3);
        jMenu3.add(jSeparator3);

        jMenuItem4.setText("Thêm lịch");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clickAddCalendar(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Thống kê");

        revenue.setText("Doanh thu");
        revenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clickShowRevenue(evt);
            }
        });
        jMenu4.add(revenue);
        jMenu4.add(jSeparator4);

        buyTicket.setText("Vé bán");
        buyTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clickShowBuyTicket(evt);
            }
        });
        jMenu4.add(buyTicket);
        jMenu4.add(jSeparator5);

        movie.setText("Phim chiếu");
        movie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clickShowMovie(evt);
            }
        });
        jMenu4.add(movie);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clickShowAllStaff(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickShowAllStaff
        // TODO add your handling code here:
        this.addPanel(new FrameInfoStaff());
    }//GEN-LAST:event_clickShowAllStaff

    private void clickAddStaff(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickAddStaff
        // TODO add your handling code here:
        this.addPanel(new FrameAddStaff());
    }//GEN-LAST:event_clickAddStaff

    private void allMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allMovieActionPerformed
        // TODO add your handling code here:
        this.addPanel(new FrameInfoMovie());
    }//GEN-LAST:event_allMovieActionPerformed

    private void clickAddCalendar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickAddCalendar
        // TODO add your handling code here:
        this.addPanel(new FrameAddCalendar());
    }//GEN-LAST:event_clickAddCalendar

    private void clickAllCalendar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickAllCalendar
        // TODO add your handling code here:
        this.addPanel(new FrameInfoCalendar());
    }//GEN-LAST:event_clickAllCalendar

    private void clickShowRevenue(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickShowRevenue
        // TODO add your handling code here:
        this.addPanel(new FrameStatisticalRevenue());
    }//GEN-LAST:event_clickShowRevenue

    private void clickShowBuyTicket(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickShowBuyTicket
        // TODO add your handling code here:
        this.addPanel(new FrameStatisticalTicket());
    }//GEN-LAST:event_clickShowBuyTicket

    private void clickShowMovie(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickShowMovie
        // TODO add your handling code here:
        this.addPanel(new FrameStatisticalMovie());
    }//GEN-LAST:event_clickShowMovie
    private void addPanel(Component panel) {
        jPanel1.setLayout(new BorderLayout());
        jPanel1.removeAll();
        jPanel1.add(panel);
        jPanel1.validate();
    }

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addMovie;
    private javax.swing.JMenuItem allMovie;
    private javax.swing.JMenuItem buyTicket;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JMenuItem movie;
    private javax.swing.JMenuItem revenue;
    // End of variables declaration//GEN-END:variables
}
