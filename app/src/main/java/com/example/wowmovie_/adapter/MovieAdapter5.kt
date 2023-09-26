package com.example.wowmovie_.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wowmovie_.R
import com.example.wowmovie_.model.Movie3

import com.example.wowmovie_.model.TopRatedRuItem



class MovieAdapter5(    private val movieTopResult : ArrayList<TopRatedRuItem>
) : RecyclerView.Adapter<MovieAdapter5.MovieViewHolder>() {

    //    private val collection: ArrayList<ResultsItem> = ArrayList()
    var itemClick: ((Int) -> Unit)? = null
    fun submitLists(movieTopResult: ArrayList<TopRatedRuItem>) {
        this.movieTopResult.clear()
        this.movieTopResult.addAll(movieTopResult)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_big, parent, false)
        return MovieViewHolder(view)
    }


    override fun getItemCount() = movieTopResult.size


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.apply {
            val movieTopResult = movieTopResult[position]
            Glide.with(ivMovieImage).load("https://image.tmdb.org/t/p/w500${movieTopResult.posterPath}").into(ivMovieImage)
            tvMovieName.text = movieTopResult.title
            tvBall.text = movieTopResult.voteAverage.toString()
            tvYear.text = movieTopResult.releaseDate




            llItemMovieMini.setOnClickListener {

            }

        }


    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMovieImage = itemView.findViewById<ImageView>(R.id.iv_movie_image1)
        val llItemMovieMini = itemView.findViewById<LinearLayout>(R.id.ll_item_movie_mini1)
        val tvMovieName = itemView.findViewById<TextView>(R.id.tv_movie_name1)
        val tvBall = itemView.findViewById<TextView>(R.id.tv_ball1)
        val tvYear = itemView.findViewById<TextView>(R.id.tv_movie_janr1)


    }

}