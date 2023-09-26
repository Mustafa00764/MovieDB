package com.example.wowmovie_.data.remote

import com.example.wowmovie_.model.MovieID
import com.example.wowmovie_.model.NowPlaingRU
import com.example.wowmovie_.model.PopularRU
import com.example.wowmovie_.model.TopRatedRU

import com.example.wowmovie_.model.UpcomingRU
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDBService {
    @GET("movie/{movie_id}")
    fun getAllProducts(@Path("{movie_id}") id: String): Call<MovieID>


    @GET("movie/now_playing?language=ru-RU&page=1")
    fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Call<NowPlaingRU>

    @GET("movie/popular?language=ru-RU&page=1")
    fun getMoviePopular(
        @Query("page") page:Int
    ): Call<PopularRU>

    @GET("movie/top_rated?language=ru-RU&page=1")
    fun getMovieRTopResult(
        @Query("page") page: Int
    ): Call<TopRatedRU>

    @GET("movie/upcoming?language=ru-RU&page=1")
    fun getMovieUpcoming(
        @Query("page") page: Int
    ): Call<UpcomingRU>
}