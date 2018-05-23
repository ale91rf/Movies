package com.app.movies.ui.di;


import android.content.Context;
import android.content.res.Resources;

import com.app.movies.ui.App;
import com.app.movies.domain.interactor.ThreadExecutor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    Context providesContext(App application) {
        return application;
    }


    @Provides
    @Singleton
    @Named("subscriberOn")
    ThreadExecutor providesSubscriberOnThreadExecutor() {
        return new ThreadExecutor(Schedulers.newThread());
    }

    @Provides
    @Singleton
    @Named("observerOn")
    ThreadExecutor providesObserverOnThreadExecutor() {
        return new ThreadExecutor(AndroidSchedulers.mainThread());
    }

}
