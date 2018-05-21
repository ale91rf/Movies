package com.app.movies.ui.mapper;

public class MovieViewModel {

    private String image;
    private String title;
    private String year;
    private String overview;

    public MovieViewModel(String image, String title, String year, String overview) {
        this.image = image;
        this.title = title;
        this.year = year;
        this.overview = overview;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getOverview() {
        return overview;
    }
}
