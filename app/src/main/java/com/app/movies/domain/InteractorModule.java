package com.app.movies.domain;

import com.app.movies.domain.interactor.GetPopularMoviesInteractor;
import com.app.movies.domain.interactor.ThreadExecutor;
import com.app.movies.domain.repository.MoviesRepositoryContractor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    @Singleton
    GetPopularMoviesInteractor providesGetPopularMoviesInteractor(@Named("subscriberOn") final ThreadExecutor subscriberOn,
                                                                  @Named("observerOn") final ThreadExecutor observerOn,
                                                                  final MoviesRepositoryContractor moviesRepositoryContractor) {
        return new GetPopularMoviesInteractor(subscriberOn,
                observerOn,
                moviesRepositoryContractor);
    }
}
