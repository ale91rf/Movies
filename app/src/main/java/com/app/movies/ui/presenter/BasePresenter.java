package com.app.movies.ui.presenter;

import android.support.annotation.CallSuper;

import com.app.movies.ui.viewModel.MovieViewModel;
import com.app.movies.ui.view.BaseView;

import java.util.List;

public abstract class BasePresenter<T extends BaseView> {

    private T view;

    @CallSuper
    public void setView(T view) {
        this.view = view;
    }
    public boolean isViewAttached() {
        return view != null;
    }

    protected void showProgress() {
        if (isViewAttached()) {
            view.showProgress();
        }
    }
    protected void hideProgress() {
        if (isViewAttached()) {
            view.hideProgress();
        }
    }

    protected void setMovies(List<MovieViewModel> movies) {
        if (isViewAttached()) {
            view.setMovies(movies);
        }
    }

    protected void showError() {
        if (isViewAttached()) {
            view.showError();
        }
    }

    @CallSuper
    public void onDestroy() {
        view = null;
    }
}
