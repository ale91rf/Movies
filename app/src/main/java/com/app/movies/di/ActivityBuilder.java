package com.app.movies.di;

import com.app.movies.ui.activity.BaseActivity;
import com.app.movies.ui.activity.PopularMoviesActivities;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract BaseActivity bindBaseActivity();

    @ContributesAndroidInjector
    abstract PopularMoviesActivities bindPopularMoviesActivity();
}
