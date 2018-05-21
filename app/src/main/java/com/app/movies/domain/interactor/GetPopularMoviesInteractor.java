package com.app.movies.domain.interactor;


import com.app.movies.domain.repository.MoviesRepositoryContractor;

import javax.inject.Inject;

import io.reactivex.SingleObserver;

public class GetPopularMoviesInteractor extends BaseInteractor {

    private MoviesRepositoryContractor moviesRepositoryContractor;


    @Inject
    public GetPopularMoviesInteractor(ThreadExecutor subscriberOn,
                                      ThreadExecutor observerOn,
                                      MoviesRepositoryContractor moviesRepositoryContractor) {
        super(subscriberOn, observerOn);
        this.moviesRepositoryContractor = moviesRepositoryContractor;
    }

    public void getPopularMovies(final String page, SingleObserver singleObserver) {
        execute(moviesRepositoryContractor.getPopularMovies(page), singleObserver);
    }

}
