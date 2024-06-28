/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.controller;

import com.mycompany.ticket_manager.model.Movie;
import com.mycompany.ticket_manager.model.Response;
import com.mycompany.ticket_manager.service.MovieService;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chien
 */
public class MovieController {

    private MovieService movieService;

    public MovieController() {
        this.movieService = new MovieService();
    }

    public Response<List<Movie>> getAllMovie() {
        return this.movieService.getAllMovie();
    }

    public Response<List<Movie>> getAllMovieOk() {
        return this.movieService.getAllMovieOk();
    }

    public Response<List<Object>> stopMovie(String id) {
        return this.movieService.stopMovie(id);
    }

    public Response<Movie> editMovie(Movie movie) {
        return this.movieService.editMovie(movie);
    }

    public Response<List<Movie>> findMovie(String id, String name) {
        return this.movieService.findMovie(id, name);
    }

    public Response<List<Movie>> findMovieStatus(boolean status) {
        return this.movieService.findStatus(status);
    }

    public Response<List<Map<String, String>>> getMovieInDate(long timestamp) {
        return this.movieService.getMovieInDate(timestamp);
    }

}
