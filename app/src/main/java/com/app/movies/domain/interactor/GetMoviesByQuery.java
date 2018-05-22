package com.app.movies.domain.interactor;

import com.app.movies.domain.repository.MoviesRepositoryContractor;

import javax.inject.Inject;

import io.reactivex.SingleObserver;

public class GetMoviesByQuery extends BaseInteractor {

    private MoviesRepositoryContractor moviesRepositoryContractor;

    @Inject
    public GetMoviesByQuery(ThreadExecutor subscriberOn,
                            ThreadExecutor observerOn,
                            MoviesRepositoryContractor moviesRepositoryContractor) {
        super(subscriberOn, observerOn);
        this.moviesRepositoryContractor = moviesRepositoryContractor;
    }

    public void getMoviesByQuery(final String page,
                                 final String query,
                                 SingleObserver singleObserver) {
        execute(moviesRepositoryContractor.getMoviesByQuery(page, query),
                singleObserver);
    }
}
