package com.app.movies.data.mapper;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.app.movies.data.model.MovieModel;
import com.app.movies.data.model.MoviesDataModel;
import com.app.movies.domain.model.Movie;
import com.app.movies.domain.model.MoviesData;

import java.util.List;

import javax.inject.Inject;


public class MoviesMapper {

    @Inject
    public MoviesMapper() {
        //Dagger
    }

    public MoviesData transformMovies(MoviesDataModel moviesDataModel) {
        List<Movie> movies = Stream.of(moviesDataModel.getResults())
                .map(movieModel -> transformMovie(movieModel))
                .collect(Collectors.toList());

        return new MoviesData(moviesDataModel.getPage(),
                moviesDataModel.getTotal_results(),
                moviesDataModel.getTotal_pages(),
                movies);
    }

    private Movie transformMovie(MovieModel movieModel) {
        return new Movie(movieModel.getTitle(),
                movieModel.getOverview(),
                movieModel.getPoster_path(),
                movieModel.getRelease_date());
    }
}
