package com.app.movies.ui.presenter;



import com.app.movies.domain.interactor.GetMoviesByQueryInteractor;
import com.app.movies.domain.model.MoviesData;
import com.app.movies.ui.mapper.MoviesViewModelMapper;
import com.app.movies.ui.util.Utilities;
import com.app.movies.ui.view.SearchMoviesView;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class SearchMoviesPresenter extends BasePresenter<SearchMoviesView> {

    private final GetMoviesByQueryInteractor getMoviesByQueryInteractor;

    private String query;

    @Inject
    public SearchMoviesPresenter(MoviesViewModelMapper moviesViewModelMapper,
                                 GetMoviesByQueryInteractor getMoviesByQueryInteractor) {
        super(moviesViewModelMapper);
        this.getMoviesByQueryInteractor = getMoviesByQueryInteractor;
    }

    @Override
    protected void getMoreMovies() {
        getMovies(false, query, Utilities.intToString(getNextPage()));
    }

    public void getMoviesByQuery(String query) {
        if (query.length() > 0) {
            this.query = query;
            getMovies(true, query, Utilities.intToString(1));
        } else {
            doInView(view -> view.setUpMovies(new ArrayList<>()));
        }

    }

    private void getMovies(boolean setUp, String query, String page) {
        showProgress();
        getMoviesByQueryInteractor.getMoviesByQuery(page, query,
                new SingleObserver<MoviesData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MoviesData moviesData) {
                        hideProgress();
                        setData(moviesData.getPage(), moviesData.getTotalPages());
                        setMovies(moviesViewModelMapper.transformMovies(moviesData.getResults()), setUp);
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgress();
                        showError();
                    }
                });
    }
}
