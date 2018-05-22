package com.app.movies.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.movies.R;
import com.app.movies.ui.adapter.MoviesAdapter;
import com.app.movies.ui.view.BaseView;
import com.app.movies.ui.viewModel.MovieViewModel;

import java.util.List;

import butterknife.BindView;
import dagger.android.AndroidInjection;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @BindView(R.id.loading)
    ProgressBar loading;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MoviesAdapter moviesAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        PaginationListener paginationListener = new PaginationListener(linearLayoutManager);
        recyclerView.addOnScrollListener(paginationListener);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void setMovies(List<MovieViewModel> movies) {
        if (moviesAdapter == null) {
            moviesAdapter = new MoviesAdapter(movies);
            recyclerView.setAdapter(moviesAdapter);
        } else {
            moviesAdapter.addMovies(movies);
            recyclerView.post(() -> moviesAdapter.notifyItemInserted(movies.size() - 1));
        }
    }

    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), R.string.error_text, Toast.LENGTH_SHORT).show();
    }

    private class PaginationListener extends RecyclerView.OnScrollListener {
        LinearLayoutManager linearLayoutManager;

        public PaginationListener(LinearLayoutManager linearLayoutManager) {
            this.linearLayoutManager = linearLayoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (dy > 0) {
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                if (firstVisibleItemPosition + visibleItemCount == totalItemCount && totalItemCount != 0) {
                    getMoreMovies();
                }
            }
        }
    }

    protected abstract void getMoreMovies();
}
