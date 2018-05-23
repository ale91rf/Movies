package com.app.movies.ui.viewModel;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieViewModel that = (MovieViewModel) o;

        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        return overview != null ? overview.equals(that.overview) : that.overview == null;
    }

    @Override
    public int hashCode() {
        int result = image != null ? image.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (overview != null ? overview.hashCode() : 0);
        return result;
    }
}
