package com.app.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class PopularMoviesActivities extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @OnClick(R.id.fab)
    void onFabClick() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_popular_movies);
        ButterKnife.bind(this);
    }

}
