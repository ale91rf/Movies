package com.app.movies.data.di;

import com.app.movies.data.networking.ApiKeyInterceptor;
import com.app.movies.data.networking.MoviesService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ServiceModule {

    private static final String BASE_URL = "http://api.themoviedb.org/";

    @Provides
    @Singleton
    MoviesService providesMoviesService(Retrofit retrofit) {
        return retrofit.create(MoviesService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient(ApiKeyInterceptor apiKeyInterceptor) {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(apiKeyInterceptor)
                .build();
    }

}
