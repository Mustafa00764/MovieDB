package com.example.wowmovie_.ui.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.adapter.HomeAdapter
import com.example.wowmovie_.adapter.MovieAdapter

import com.example.wowmovie_.data.remote.TMDBClient
import com.example.wowmovie_.model.MainModel
import com.example.wowmovie_.model.Movie
import com.example.wowmovie_.model.Movie3
import com.example.wowmovie_.model.Movie4

import com.example.wowmovie_.model.NowItemRU
import com.example.wowmovie_.model.NowPlaingRU

import com.example.wowmovie_.model.PopularRU
import com.example.wowmovie_.model.ResultsItemRU
import com.example.wowmovie_.model.TopRatedRU
import com.example.wowmovie_.model.TopRatedRuItem

import com.example.wowmovie_.model.UpcomingRU
import com.example.wowmovie_.model.UpcomingRUItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragmentMyMovie : Fragment(R.layout.fragment_home_my_movie) {
    lateinit var movie: ArrayList<NowItemRU>
    lateinit var main:ArrayList<ResultsItemRU>
    lateinit var movie3:ArrayList<TopRatedRuItem>
    lateinit var movie4:ArrayList<UpcomingRUItem>
    lateinit var adapter: HomeAdapter
    lateinit var adapter2: MovieAdapter
lateinit var vse:TextView



    //    lateinit var adapter: MovieAdapter
    lateinit var movies: ArrayList<MainModel>
    lateinit var moviePopular: ArrayList<Movie>
    lateinit var movieTopResult: ArrayList<Movie3>
    lateinit var movieUpcoming: ArrayList<Movie4>
    

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initView(view)
        initViews(view)
        initView4(view)
        initView3(view)
    }

    private fun initView4(view: View) {
        movie4 = ArrayList()
        movieUpcoming = ArrayList()
        adapter2=MovieAdapter(requireContext(), movieModel = movie)
        adapter = HomeAdapter(requireContext())
        val rvMain2 = view.findViewById<RecyclerView>(R.id.rvMain)
        rvMain2.adapter = adapter
        fetchNowPlayingMovie4()
        adapter.onClick={
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }

    }

    private fun fetchNowPlayingMovie4() {
        TMDBClient.api.getMovieUpcoming(10).enqueue(object :Callback<UpcomingRU>{
            override fun onResponse(call: Call<UpcomingRU>, response: Response<UpcomingRU>) {
                if (response.isSuccessful){
                    movie4.clear()
                    response.body()?.results?.forEach {
                        movie4.add(it)
                    }
                    movieUpcoming.add(Movie4("Предстоящие", movie4))
                    adapter.submitLists4(movieUpcoming)
                }else{
                    Log.d("@@@@@@", "onResponse:${response.code()} ")
                    Log.d("@@@@@@", "onResponse:${response.errorBody()} ")

                }
            }

            override fun onFailure(call: Call<UpcomingRU>, t: Throwable) {

            }

        })
    }

    private fun initView3(view: View) {
        movie3 = ArrayList()
        movieTopResult = ArrayList()
        adapter = HomeAdapter(requireContext())
        val rvMain2 = view.findViewById<RecyclerView>(R.id.rvMain)
        rvMain2.adapter = adapter
        fetchNowPlayingMovie3()

    }

    private fun fetchNowPlayingMovie3() {
        TMDBClient.api.getMovieRTopResult(2).enqueue(object :Callback<TopRatedRU>{
            override fun onResponse(call: Call<TopRatedRU>, response: Response<TopRatedRU>) {
                if (response.isSuccessful){
                    movie3.clear()
                    response.body()?.results?.forEach {
                        movie3.add(it)
                    }
                    movieTopResult.add(Movie3("Самые популярные", movie3))
                    adapter.submitLists3(movieTopResult)
                }else{
                    Log.d("@@@@@@", "onResponse:${response.code()} ")
                    Log.d("@@@@@@", "onResponse:${response.errorBody()} ")
                }
            }

            override fun onFailure(call: Call<TopRatedRU>, t: Throwable) {

            }

        })
    }


    private fun initViews(view: View) {
            movie = ArrayList()
            movies = ArrayList()
        adapter2 = MovieAdapter(requireContext(), movieModel = movie)


            adapter = HomeAdapter(requireContext())
            val rvMain = view.findViewById<RecyclerView>(R.id.rvMain)
            rvMain.adapter = adapter
            fetchNowPlayingMovies()
        adapter2.itemClick={
            val buldle = Bundle()
            buldle.putString("movieId",movie[it].id.toString())
            Toast.makeText(requireContext(), "on click", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragmentMyMovie_to_moviesFragment,buldle)
        }

    }


    private fun fetchNowPlayingMovies() {

        Log.d("@@@@@@", "fetchNowPlayingMovies: ")
        TMDBClient.api.getNowPlayingMovies(1).enqueue(object : Callback<NowPlaingRU> {
            override fun onResponse(
                call: Call<NowPlaingRU>,
                response: Response<NowPlaingRU>
            ) {
                if (response.isSuccessful) {
//                    ludshiyFilm()
                    movie.clear()
                    Log.d("@@@@@@", "onResponse: ${response.body()}")
                    response.body()?.results?.forEach {
                        movie.add(it)
                    }
                    movies.add(MainModel("Сейчас играют", movie))
                    adapter.submitLists(movies)

                } else {
                    Log.d("@@@@@@", "onResponse:${response.code()} ")
                    Log.d("@@@@@@", "onResponse:${response.errorBody()} ")
                }
            }

            override fun onFailure(call: Call<NowPlaingRU>, t: Throwable) {
                Log.d("@@@@@@", "onFailure:${t.localizedMessage} : ${t.message} : ${t.cause} ")
            }

        })


    }
    private fun initView(view: View) {
        main = ArrayList()
        val vse = view.findViewById<ImageView>(R.id.iv)
        moviePopular = ArrayList()
        adapter = HomeAdapter(requireContext())
        val rvMain2 = view.findViewById<RecyclerView>(R.id.rvMain)
        rvMain2.adapter = adapter
        fetchNowPlayingMovie()

    }
    fun fetchNowPlayingMovie() {

        Log.d("@@@@@@", "fetchNowPlayingMovie: ")
        TMDBClient.api.getMoviePopular(1).enqueue(object :Callback<PopularRU>{
            override fun onResponse(call: Call<PopularRU>, response: Response<PopularRU>) {
                if (response.isSuccessful){
                    main.clear()
                    Log.d("@@@@@@", "onResponse: ${response.body()}")
                    response.body()?.results?.forEach {
                        main.add(it)
                    }
                    moviePopular.add(Movie("Популярные",main))
                    adapter.submitList(moviePopular)
                }else{
                    Log.d("@@@@@@", "onResponse:${response.code()} ")
                    Log.d("@@@@@@", "onResponse:${response.errorBody()} ")
                }
            }

            override fun onFailure(call: Call<PopularRU>, t: Throwable) {
                Log.d("@@@@@@", "onFailure:${t.localizedMessage} : ${t.message} : ${t.cause} ")
            }

        })


    }

//    fun ludshiyFilm() {
//        TMDBClient.api.getMoviePopular().enqueue(object : Callback<Moviespoplar>{
//            override fun onResponse(
//                call: Call<Moviespoplar>,
//                response: Response<Moviespoplar>
//            ) {
//                if (response.isSuccessful){
//                    main.clear()
//                    response.body()?.results?.forEach {
//                        main.add(it)
//                    }
//                    moviePopular.add(Movie("Popular",main))
//                    adapter.submovieList(moviePopular)
//                }else{
//
//                }
//            }
//
//            override fun onFailure(call: Call<Moviespoplar>, t: Throwable) {
//
//            }
//
//        })
//    }

}