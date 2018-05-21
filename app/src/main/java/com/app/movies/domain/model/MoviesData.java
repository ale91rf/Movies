package com.app.movies.domain.model;

import java.util.List;

public class MoviesData {

    private int page;
    private int totalResults;
    private int totalPages;
    private List<Movie> results;

    public MoviesData(int page, int totalResults, int totalPages, List<Movie> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }
}
