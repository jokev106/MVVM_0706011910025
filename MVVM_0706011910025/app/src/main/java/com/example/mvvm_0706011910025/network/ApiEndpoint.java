package com.example.mvvm_0706011910025.network;

import com.example.mvvm_0706011910025.model.MovieResponse;
import com.example.mvvm_0706011910025.model.TvShowResponse;
import com.example.mvvm_0706011910025.model.cast.CastResponse;
import com.example.mvvm_0706011910025.model.genre.GenreResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpoint {

    @GET("discover/movie")  //https://api.themoviedb.org/3/discover/movie
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey);

    @GET("discover/tv")  //https://developers.themoviedb.org/3/discover/tv-discover
    Call<TvShowResponse> getTvShow(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}") //https://developers.themoviedb.org/3/movies/get-movie-details
    Call<GenreResponse> getGenre(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("{type}/{id}") // get details and genres of specific movie / tv
    Call<GenreResponse> getGenres(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);

    @GET("{type}/{id}/credits") // get casts of specific movie / tv shows
    Call<CastResponse> getCasts(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);

}
