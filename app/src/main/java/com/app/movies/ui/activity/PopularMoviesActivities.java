package com.app.movies.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.movies.R;
import com.app.movies.ui.presenter.PopularMoviesPresenter;
import com.app.movies.ui.view.PopularMoviesView;


import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class PopularMoviesActivities extends BaseActivity implements PopularMoviesView {

    @OnClick(R.id.fab)
    void onFabClick() {
        popularMoviesPresenter.onFabClicked();
    }

    @Inject
    PopularMoviesPresenter popularMoviesPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_popular_movies);
        ButterKnife.bind(this);
        initView();
        popularMoviesPresenter.setView(this);
        popularMoviesPresenter.start();
    }

    @Override
    protected void onDestroy() {
        popularMoviesPresenter.onDestroy();
        super.onDestroy();
    }


    @Override
    public void goToSearchScreen() {
        SearchMoviesActivity.startActivity(this);
    }


    @Override
    protected void getMoreMovies() {
        popularMoviesPresenter.getMoreMovies();
    }
}
