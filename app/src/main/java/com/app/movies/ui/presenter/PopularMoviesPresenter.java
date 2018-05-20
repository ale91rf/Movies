package com.app.movies.ui.presenter;

import com.app.movies.ui.view.PopularMoviesView;

import javax.inject.Inject;

public class PopularMoviesPresenter extends BasePresenter<PopularMoviesView> {

    @Inject
    public PopularMoviesPresenter() {

    }

    public void start() {
        showProgress();
        //TODO get movies
    }

}
