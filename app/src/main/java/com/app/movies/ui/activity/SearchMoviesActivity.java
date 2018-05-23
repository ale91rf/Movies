package com.app.movies.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.movies.R;
import com.app.movies.ui.presenter.SearchMoviesPresenter;
import com.app.movies.ui.view.SearchMoviesView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class SearchMoviesActivity extends BaseActivity implements SearchMoviesView {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SearchMoviesActivity.class));
    }

    @Inject
    SearchMoviesPresenter searchMoviesPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        ButterKnife.bind(this);
        initView();
        searchMoviesPresenter.setView(this);
    }


    @Override
    protected void onDestroy() {
        searchMoviesPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void getMoreMovies() {
        searchMoviesPresenter.onGetMoreMovies();
    }
}
