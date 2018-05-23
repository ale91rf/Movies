package com.app.movies.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

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
    protected void initView() {
        super.initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getDelegate().getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.expandActionView();
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchMoviesPresenter.getMoviesByQuery(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
