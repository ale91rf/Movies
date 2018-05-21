package com.app.movies.ui.di;


import com.app.movies.ui.App;
import com.app.movies.data.di.RepositoryModule;
import com.app.movies.data.di.ServiceModule;
import com.app.movies.domain.InteractorModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ActivityBuilder.class,
        ServiceModule.class,
        RepositoryModule.class,
        InteractorModule.class
})

public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App application);

        ApplicationComponent build();

    }

    void inject(App application);
}
