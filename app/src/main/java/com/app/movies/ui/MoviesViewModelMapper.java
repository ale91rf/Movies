package com.app.movies.ui;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.app.movies.domain.model.Movie;
import com.app.movies.ui.util.Utilities;
import com.app.movies.ui.mapper.MovieViewModel;

import java.util.List;

import javax.inject.Inject;

public class MoviesViewModelMapper {

    @Inject
    public MoviesViewModelMapper() {
        //Dagger
    }

    public List<MovieViewModel> transformMovies(List<Movie> movies) {
        return Stream.of(movies)
                .map(movie -> transformMovie(movie))
                .collect(Collectors.toList());
    }

    private MovieViewModel transformMovie(Movie movie) {
        return new MovieViewModel(Utilities.transformImagePath(movie.getPosterPath()),
                movie.getTitle(),
                Utilities.transformDate(movie.getYear()),
                movie.getOverview());
    }
}
