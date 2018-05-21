package com.app.movies.ui.presenter;

import com.app.movies.domain.interactor.GetPopularMoviesInteractor;
import com.app.movies.domain.model.MoviesData;
import com.app.movies.ui.MoviesViewModelMapper;
import com.app.movies.ui.util.Utilities;
import com.app.movies.ui.view.PopularMoviesView;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class PopularMoviesPresenter extends BasePresenter<PopularMoviesView> {

    private final MoviesViewModelMapper moviesViewModelMapper;
    private final GetPopularMoviesInteractor getPopularMoviesInteractor;
    private int page = 1;

    @Inject
    public PopularMoviesPresenter(GetPopularMoviesInteractor getPopularMoviesInteractor,
                                  MoviesViewModelMapper moviesViewModelMapper) {
        this.getPopularMoviesInteractor = getPopularMoviesInteractor;
        this.moviesViewModelMapper = moviesViewModelMapper;
    }

    public void start() {
        getMovies(page);
    }

    private void getMovies(int page) {
        showProgress();
        getPopularMoviesInteractor.getPopularMovies(Utilities.intToString(page), new SingleObserver<MoviesData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(MoviesData moviesData) {
                hideProgress();
                setMovies(moviesViewModelMapper.transformMovies(moviesData.getResults()));
            }

            @Override
            public void onError(Throwable e) {
                hideProgress();
            }
        });
    }
}
