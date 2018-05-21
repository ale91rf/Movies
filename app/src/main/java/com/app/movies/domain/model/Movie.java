package com.app.movies.domain.model;


public class Movie {

    private String title;
    private String overview;
    private String posterPath;
    private String year;

    public Movie(String title, String overview, String posterPath, String year) {
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getYear() {
        return year;
    }
}
