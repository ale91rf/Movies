package com.app.movies.data.di;

import com.app.movies.data.mapper.MoviesMapper;
import com.app.movies.data.networking.MoviesService;
import com.app.movies.data.repository.MoviesRepository;
import com.app.movies.domain.repository.MoviesRepositoryContractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    MoviesRepositoryContractor providesMoviesRepository(final MoviesService moviesService,
                                                        final MoviesMapper moviesMapper) {
        return new MoviesRepository(moviesService, moviesMapper);
    }
}
