package com.app.movies.data.model;

import java.util.List;

public class MovieModel {
    private double popularity;
    private int id;
    private boolean video;
    private int vote_count;
    private double vote_average;
    private String title;
    private String release_date;
    private String original_language;
    private String original_title;
    private List<Integer> genre_ids;
    private String backdrop_path;
    private boolean adult;
    private String overview;
    private String poster_path;

    public double getPopularity() {
        return popularity;
    }

    public int getId() {
        return id;
    }

    public boolean isVideo() {
        return video;
    }

    public int getVote_count() {
        return vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }
}
