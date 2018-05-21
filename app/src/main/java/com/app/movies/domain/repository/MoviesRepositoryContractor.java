package com.app.movies.domain.repository;

import com.app.movies.domain.model.MoviesData;

import io.reactivex.Single;

public interface MoviesRepositoryContractor {

    Single<MoviesData> getPopularMovies(final String page);
}
