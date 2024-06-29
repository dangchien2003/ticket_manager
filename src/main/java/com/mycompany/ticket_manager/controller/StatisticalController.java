/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.controller;

import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.service.StatisticalRevenueService;
import com.mycompany.ticket_manager.service.StatisticalMovieService;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chien
 */
public class StatisticalController {

    StatisticalRevenueService statisticalRevenueService;
    StatisticalMovieService statisticalMovieService;

    public StatisticalController() {
        this.statisticalRevenueService = new StatisticalRevenueService();
        this.statisticalMovieService = new StatisticalMovieService();
    }

    public Response<List<Map<String, Object>>> revenue(int style, String day, String month, String year) {
        return this.statisticalRevenueService.revenue(style, day, month, year);
    }

    public Response<List<Map<String, Object>>> movie(int style, String movie, String day, String month, String year) {
        return this.statisticalMovieService.movie(style, movie, day, month, year);
    }
}
