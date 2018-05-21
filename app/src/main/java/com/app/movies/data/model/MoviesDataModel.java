package com.app.movies.data.model;

import java.util.List;

public class MoviesDataModel {

    private int page;
    private int total_results;
    private int total_pages;
    private List<MovieModel> results;

    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<MovieModel> getResults() {
        return results;
    }
}
