package com.app.movies.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.movies.R;
import com.app.movies.ui.viewModel.MovieViewModel;
import com.app.movies.ui.adapter.MoviesAdapter;
import com.app.movies.ui.presenter.PopularMoviesPresenter;
import com.app.movies.ui.view.PopularMoviesView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class PopularMoviesActivities extends BaseActivity implements PopularMoviesView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @OnClick(R.id.fab)
    void onFabClick() {

    }

    @Inject
    PopularMoviesPresenter popularMoviesPresenter;

    private MoviesAdapter moviesAdapter;

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
    void initView() {
        moviesAdapter = new MoviesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void goToSearchScreen() {
        //TODO
    }

    @Override
    public void setMovies(List<MovieViewModel> movies) {
        moviesAdapter.setViewModels(movies);
    }
}
