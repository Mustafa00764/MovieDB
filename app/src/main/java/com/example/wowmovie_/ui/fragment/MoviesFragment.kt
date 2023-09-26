package com.example.wowmovie_.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.wowmovie_.R
import com.example.wowmovie_.data.remote.TMDBClient
import com.example.wowmovie_.model.MovieID

import com.example.wowmovie_.model.MovieViewModel
import com.example.wowmovie_.model.NowItemRU
import com.example.wowmovie_.util.MovieFragmentArgs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesFragment : Fragment(R.layout.fragment_movies) {
    var id = ""
val args:MovieFragmentArgs by navArgs()
    private lateinit var tvtitle:TextView
    private lateinit var tvName:TextView
    private lateinit var tvopisanie:TextView
    private lateinit var tvdata:TextView
    private lateinit var tvball:TextView
    private lateinit var ll : LinearLayout
    private lateinit var viewM:MovieViewModel


    lateinit var opisanie:ArrayList<NowItemRU>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        id=args.movieId
        tvopisanie = view.findViewById(R.id.tv_opisanie1)
        tvName  = view.findViewById(R.id.tv_Movie_NAME)
        tvball = view.findViewById(R.id.tv_ball55)

        loadList()
    }



    private fun loadList() {

        TMDBClient.api.getAllProducts(id).enqueue(object :Callback<MovieID>{
            override fun onResponse(call: Call<MovieID>, response: Response<MovieID>) {

                if (response.isSuccessful){

                    setData(response.body())

                }

            }

            override fun onFailure(call: Call<MovieID>, t: Throwable) {

            }
        })

    }

    private fun setData(body: MovieID?) {

        tvName.text=body?.title
        tvdata.text=body?.releaseDate
        tvball.text=body?.voteAverage.toString()

    }


}