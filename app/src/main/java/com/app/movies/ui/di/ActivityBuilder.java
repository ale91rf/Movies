package com.app.movies.ui.di;

import com.app.movies.ui.activity.BaseActivity;
import com.app.movies.ui.activity.PopularMoviesActivities;
import com.app.movies.ui.activity.SearchMoviesActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract BaseActivity bindBaseActivity();

    @ContributesAndroidInjector
    abstract PopularMoviesActivities bindPopularMoviesActivity();

    @ContributesAndroidInjector
    abstract SearchMoviesActivity bindSearchMoviesActivity();
}
