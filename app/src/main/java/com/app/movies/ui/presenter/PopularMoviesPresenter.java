package com.app.movies.ui.presenter;

import com.app.movies.domain.interactor.GetPopularMoviesInteractor;
import com.app.movies.domain.model.MoviesData;
import com.app.movies.ui.mapper.MoviesViewModelMapper;
import com.app.movies.ui.util.Utilities;
import com.app.movies.ui.view.PopularMoviesView;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class PopularMoviesPresenter extends BasePresenter<PopularMoviesView> {

    private final GetPopularMoviesInteractor getPopularMoviesInteractor;


    @Inject
    public PopularMoviesPresenter(GetPopularMoviesInteractor getPopularMoviesInteractor,
                                  MoviesViewModelMapper moviesViewModelMapper) {
        super(moviesViewModelMapper);
        this.getPopularMoviesInteractor = getPopularMoviesInteractor;
    }

    public void start() {
        getMovies();
    }

    protected void getMovies() {
        showProgress();
        getPopularMoviesInteractor.getPopularMovies(Utilities.intToString(getNextPage()), new SingleObserver<MoviesData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(MoviesData moviesData) {
                hideProgress();
                setData(moviesData.getPage(), moviesData.getTotalPages());
                setMovies(moviesViewModelMapper.transformMovies(moviesData.getResults()));
            }

            @Override
            public void onError(Throwable e) {
                hideProgress();
                showError();
            }
        });
    }


}
