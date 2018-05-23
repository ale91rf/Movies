package com.app.movies.ui.mapper;

import com.app.movies.data.model.MovieModel;
import com.app.movies.domain.model.Movie;
import com.app.movies.ui.viewModel.MovieViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class MoviesViewModelMapperTest {


    MoviesViewModelMapper moviesViewModelMapper = new MoviesViewModelMapper();


    @Test
    public void shouldReturnAListOfMoviesViewModel() {
        //given
        MovieViewModel movieViewModel = new MovieViewModel("https://image.tmdb.org/t/p/w500/alskldkssl.jpg",
                "Split", "2017", "best film ever");
        MovieViewModel movieViewModelTwo = new MovieViewModel("https://image.tmdb.org/t/p/w500/alskldkssl.jpg",
                "pulp fiction", "1999", "best film ever");
        List<MovieViewModel> expected = new ArrayList<>();
        expected.add(movieViewModel);
        expected.add(movieViewModelTwo);

        Movie movie = new Movie("Split", "best film ever", "alskldkssl.jpg", "2017-03-04");
        Movie movieTwo = new Movie("pulp fiction", "best film ever", "alskldkssl.jpg", "1999-03-04");

        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(movieTwo);

        //when
        List<MovieViewModel> result = moviesViewModelMapper.transformMovies(movies);

        //then
        Assert.assertEquals(expected, result);
    }

}