package com.app.movies.ui.presenter;

import android.support.annotation.CallSuper;

import com.app.movies.ui.mapper.MoviesViewModelMapper;
import com.app.movies.ui.viewModel.MovieViewModel;
import com.app.movies.ui.view.BaseView;

import java.util.List;

public abstract class BasePresenter<T extends BaseView> {

    public interface ViewFunction<V> {
        void execute(V view);
    }

    protected final MoviesViewModelMapper moviesViewModelMapper;

    protected int page = 0;
    protected boolean isLastPage = false;
    protected boolean isLoading = false;
    private T view;

    @CallSuper
    public void setView(T view) {
        this.view = view;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public BasePresenter(MoviesViewModelMapper moviesViewModelMapper) {
        this.moviesViewModelMapper = moviesViewModelMapper;
    }

    protected void doInView(ViewFunction<T> function) {
        if (isViewAttached()) {
            function.execute(view);
        }
    }

    protected void showProgress() {
        isLoading = true;
        if (isViewAttached()) {
            view.showProgress();
        }
    }

    protected void hideProgress() {
        isLoading = false;
        if (isViewAttached()) {
            view.hideProgress();
        }
    }

    protected void setMovies(List<MovieViewModel> movies, boolean setUp) {
        if (isViewAttached()) {
            if (setUp) {
                view.setUpMovies(movies);
            } else {
                view.addMovies(movies);
            }
        }
    }

    protected void showError() {
        if (isViewAttached()) {
            view.showError();
        }
    }

    protected int getNextPage() {
        return page + 1;
    }

    public void onGetMoreMovies() {
        if (!isLastPage && !isLoading) {
            getMoreMovies();
        }
    }

    protected void setData(int page, int totalPages) {
        this.page = page;
        isLastPage = page >= totalPages;
    }

    protected abstract void getMoreMovies();

    @CallSuper
    public void onDestroy() {
        view = null;
    }
}
