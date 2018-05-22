package com.app.movies.data.networking;

import com.app.movies.data.model.MoviesDataModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("4/discover/movie?sort_by=popularity.desc")
    Single<MoviesDataModel> getPopularMovies(@Query("page") String page);


    @GET("4/search/movie")
    Single<MoviesDataModel> getMoviesByQuery(@Query("page") String page,
                                             @Query("query") String query);
}
