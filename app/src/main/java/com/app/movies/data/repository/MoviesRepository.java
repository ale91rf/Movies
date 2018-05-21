package com.app.movies.data.repository;

import com.app.movies.data.mapper.MoviesMapper;
import com.app.movies.data.networking.MoviesService;
import com.app.movies.domain.repository.MoviesRepositoryContractor;
import com.app.movies.domain.model.MoviesData;

import javax.inject.Inject;

import io.reactivex.Single;

public class MoviesRepository implements MoviesRepositoryContractor {

    private MoviesService service;
    private final MoviesMapper moviesMapper;

    @Inject
    public MoviesRepository(MoviesService service,
                            MoviesMapper moviesMapper) {
        this.service = service;
        this.moviesMapper = moviesMapper;
    }

    @Override
    public Single<MoviesData> getPopularMovies(String page) {
        return service.getPopularMovies(page)
                .map(moviesMapper::transformMovies);
    }
}
