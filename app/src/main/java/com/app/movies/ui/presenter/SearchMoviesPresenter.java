package com.app.movies.ui.presenter;


import com.app.movies.domain.interactor.GetMoviesByQueryInteractor;
import com.app.movies.ui.mapper.MoviesViewModelMapper;
import com.app.movies.ui.view.SearchMoviesView;

import javax.inject.Inject;

public class SearchMoviesPresenter extends BasePresenter<SearchMoviesView> {

    private final GetMoviesByQueryInteractor getMoviesByQueryInteractor;

    @Inject
    public SearchMoviesPresenter(MoviesViewModelMapper moviesViewModelMapper,
                                 GetMoviesByQueryInteractor getMoviesByQueryInteractor) {
        super(moviesViewModelMapper);
        this.getMoviesByQueryInteractor = getMoviesByQueryInteractor;
    }

    @Override
    protected void getMoreMovies() {

    }
}
