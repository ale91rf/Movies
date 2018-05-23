package com.app.movies.ui.view;

import com.app.movies.ui.viewModel.MovieViewModel;

import java.util.List;

public interface BaseView {

    void showProgress();
    void hideProgress();
    void setUpMovies(List<MovieViewModel> movies);
    void addMovies(List<MovieViewModel> movies);


    void showError();
}
