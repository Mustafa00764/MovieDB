package com.example.wowmovie_.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.adapter.HomeAdapter
import com.example.wowmovie_.data.remote.TMDBClient
import com.example.wowmovie_.model.Movie
import com.example.wowmovie_.model.PopularRU
import com.example.wowmovie_.model.ResultsItemRU
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularMoviesFragment : Fragment(R.layout.fragment_popular_movies) {

    private lateinit var adapter:HomeAdapter
     var page = 1
    private lateinit var movie:ArrayList<ResultsItemRU>
    private lateinit var moviePopular:ArrayList<Movie>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        movie = ArrayList()
        adapter= HomeAdapter(requireContext())
        val rv = view.findViewById<RecyclerView>(R.id.rvMovies)
        rv.adapter = adapter
        loadList()
    }

    private fun loadList() {
        TMDBClient.api.getMoviePopular(page).enqueue(object : Callback<PopularRU> {
            override fun onResponse(call: Call<PopularRU>, response: Response<PopularRU>) {
                if (response.isSuccessful){
                    movie.clear()
                    Log.d("@@@@@@", "onResponse: ${response.body()}")
                    response.body()?.results?.forEach {
                        movie.add(it)
                    }
                    moviePopular.add(Movie("Популярные",movie))
                    adapter.submitList(moviePopular)
                    page++
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
}