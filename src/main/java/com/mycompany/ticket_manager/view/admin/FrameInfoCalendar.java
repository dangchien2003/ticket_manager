/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.ticket_manager.view.admin;

import com.mycompany.ticket_manager.controller.CalendarController;
import com.mycompany.ticket_manager.controller.MovieController;
import com.mycompany.ticket_manager.model.Movie;
import com.mycompany.ticket_manager.model.Response;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

/**
 *
 * @author chien
 */
public class FrameInfoCalendar extends javax.swing.JPanel {

    CalendarController calendarController;
    MovieController movieController;

    List<Movie> listMovie = new ArrayList<>();
    List<Map<String, Object>> listCalendar = new ArrayList<>();

    /**
     * Creates new form FrameInfoCalendar
     */
    public FrameInfoCalendar() {
        initComponents();
        this.movieController = new MovieController();
        this.calendarController = new CalendarController();
        this.pushMovie();
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        this.datePlay.setDate(date);
    }

    private void pushMovie() {
        Response<List<Movie>> response = this.movieController.getAllMovieOk();
        if (response.getSuccess() == false) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Thông báo lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        this.listMovie = response.getData();

        for (Movie movie : response.getData()) {
            this.movies.addItem(movie.getName());
        }
    }

    private String format(String string) {
        if (string.length() == 1) {
            string = "0" + string;
        }
        return string;
    }

    private void find() {
        long date = this.find_calendar.getDate().getTime() / 1000;
        String selectedRoom = this.cbb_room.getSelectedItem().toString();
        Response<List<Map<String, Object>>> response = this.calendarController.getCalendar(date, selectedRoom);
        if (response.getSuccess() == false) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Thông báo lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        this.listCalendar = response.getData();
        this.pushTable(response.getData());
    }

    private void pushTable(List<Map<String, Object>> dataTable) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for (Map<String, Object> row : dataTable) {
            Object[] data = {row.get("nameMovie"), "P" + row.get("room"), row.get("datePlay"), row.get("timeStart"), row.get("timeEnd")};
            tableModel.addRow(data);
        }
    }

    private void setTimeEnd() {
        int hour = ((Date) this.timeStart.getValue()).getHours();
        int minute = ((Date) this.timeStart.getValue()).getMinutes();
        Date date = new Date();
        date.setHours(hour);
        date.setMinutes(minute);
        this.timeEnd.setValue(new Date(date.getTime() + this.listMovie.get(this.movies.getSelectedIndex()).getTime() * 60 * 1000));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        find = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        datePlay = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        edit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cancle = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        movies = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        Date date = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(date,null, null, Calendar.HOUR_OF_DAY);
        timeStart = new javax.swing.JSpinner(sm);
        Date date1 = new Date();
        SpinnerDateModel smt =
        new SpinnerDateModel(date1,null, null, Calendar.HOUR_OF_DAY);
        timeEnd = new javax.swing.JSpinner(smt);
        id = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        room = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        find_calendar = new com.toedter.calendar.JDateChooser();
        cbb_room = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        find.setBackground(new java.awt.Color(51, 204, 0));
        find.setForeground(new java.awt.Color(255, 255, 255));
        find.setText("Tìm kiếm");
        find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findclickFind(evt);
            }
        });

        datePlay.setDateFormatString("dd-MM-yyyy");
        datePlay.setPreferredSize(new java.awt.Dimension(91, 32));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Tên phim");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Id");

        edit.setBackground(new java.awt.Color(0, 153, 153));
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setText("Sửa");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editclickAddCalendar(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Ngày chiếu");

        cancle.setBackground(new java.awt.Color(255, 51, 51));
        cancle.setForeground(new java.awt.Color(255, 255, 255));
        cancle.setText("Huỷ");
        cancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleclickCancle(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Giờ bắt đầu");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Giờ kết thúc");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Thông tin lịch chiếu");

        movies.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                movieschangeMovie(evt);
            }
        });

        JSpinner.DateEditor de = new JSpinner.DateEditor(this.timeStart, "HH:mm");
        timeStart.setEditor(de);
        timeStart.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                timeStartchangeTimeStart(evt);
            }
        });

        JSpinner.DateEditor det = new JSpinner.DateEditor(timeEnd, "HH:mm");
        timeEnd.setEditor(det);
        timeEnd.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeStart, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timeStart, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(timeEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        id.setEditable(false);
        id.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("Phòng chiếu");

        room.setEditable(false);
        room.setBackground(new java.awt.Color(255, 255, 255));
        room.setText("Phòng 1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7))
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(room)
                                    .addComponent(movies, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(id)
                                    .addComponent(datePlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 37, Short.MAX_VALUE)))))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movies, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(room, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(datePlay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel8)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel9)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("DANH SÁCH LỊCH CHIẾU");

        find_calendar.setDate(new Date());
        find_calendar.setDateFormatString("dd-MM-yyyy");

        cbb_room.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng 1", "Phòng 2", "Phòng 3", "Phòng 4", "Phòng 5", "Phòng 6" }));
        cbb_room.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_roomchangeRoom(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên phim", "Phòng chiếu", "Ngày chiếu", "Giờ bắt đầu", "Giờ kết thúc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableclickTable(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(find_calendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbb_room, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(find, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(85, 85, 85)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(find_calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbb_room, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(find, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void findclickFind(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findclickFind
        // TODO add your handling code here:
        this.find();
    }//GEN-LAST:event_findclickFind

    private void editclickAddCalendar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editclickAddCalendar
        // TODO add your handling code here:
        Date date = this.datePlay.getDate();
        String year = this.format(String.valueOf((date.getYear() + 1900)));
        String month = this.format(String.valueOf(date.getMonth() + 1));
        String day = this.format(String.valueOf(date.getDate()));
        Date time = (Date) this.timeStart.getValue();
        String hours = this.format(String.valueOf(time.getHours()));
        String minutes = this.format(String.valueOf(time.getMinutes()));
        String playAt = hours + ":" + minutes + ":00 " + day + "-" + month + "-" + year;
        String room = this.room.getText();
        String movie = this.listMovie.get(this.movies.getSelectedIndex()).getId();
        String id = this.id.getText();
        Response<Map<String, Object>> response = this.calendarController.updatedCalendar(id, playAt, room, movie);
        if (response.getSuccess() == false) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Thông báo lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        this.find();

        JOptionPane.showMessageDialog(null, "Cập nhật thành công", "Thông báo lỗi", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_editclickAddCalendar

    private void cancleclickCancle(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleclickCancle
        // TODO add your handling code here:
        Response<Boolean> response = this.calendarController.cancleCalendar(this.id.getText());
        if (response.getSuccess() == false) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Thông báo lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Huỷ thành công", "Thông báo lỗi", JOptionPane.INFORMATION_MESSAGE);
        this.id.setText("");
        this.find();
    }//GEN-LAST:event_cancleclickCancle

    private void movieschangeMovie(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_movieschangeMovie
        // TODO add your handling code here:
        this.setTimeEnd();
    }//GEN-LAST:event_movieschangeMovie

    private void timeStartchangeTimeStart(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeStartchangeTimeStart
        // TODO add your handling code here:
        this.setTimeEnd();
    }//GEN-LAST:event_timeStartchangeTimeStart

    private void cbb_roomchangeRoom(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_roomchangeRoom
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            return;
        }
        this.room.setText(this.cbb_room.getSelectedItem().toString());
    }//GEN-LAST:event_cbb_roomchangeRoom

    private void tableclickTable(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableclickTable
        // TODO add your handling code here:
        int indexSelected = this.table.getSelectedRow();
        Map<String, Object> dataCalendar = this.listCalendar.get(indexSelected);

        this.id.setText(dataCalendar.get("id").toString());
        this.movies.setSelectedItem(dataCalendar.get("nameMovie"));
        this.datePlay.setDate(new Date(((long) dataCalendar.get("playAt")) * 1000));
        this.timeStart.setValue(new Date(((long) dataCalendar.get("playAt")) * 1000));
    }//GEN-LAST:event_tableclickTable


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancle;
    private javax.swing.JComboBox<String> cbb_room;
    private com.toedter.calendar.JDateChooser datePlay;
    private javax.swing.JButton edit;
    private javax.swing.JButton find;
    private com.toedter.calendar.JDateChooser find_calendar;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> movies;
    private javax.swing.JTextField room;
    private javax.swing.JTable table;
    private javax.swing.JSpinner timeEnd;
    private javax.swing.JSpinner timeStart;
    // End of variables declaration//GEN-END:variables
}
