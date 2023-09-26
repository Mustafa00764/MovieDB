package com.example.wowmovie_.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.model.MainModel
import com.example.wowmovie_.model.Movie
import com.example.wowmovie_.model.Movie3
import com.example.wowmovie_.model.Movie4
import com.example.wowmovie_.model.MovieID


class HomeAdapter(val context: Context) : RecyclerView.Adapter<HomeAdapter.CollectionViewHolder>() {
var onClick:((Int) -> Unit)? = null
    private lateinit var adapter: MovieAdapter

    private val collection3 = ArrayList<Movie3>()
    fun submitLists3(collection3: ArrayList<Movie3>) {
        this.collection3.clear()
        this.collection3.addAll(collection3)
        notifyDataSetChanged()
    }
    private val collectiones = ArrayList<Movie4>()
    fun submitLists4(collectiones: ArrayList<Movie4>) {
        this.collectiones.clear()
        this.collectiones.addAll(collectiones)
        notifyDataSetChanged()
    }
    private val collection = ArrayList<MainModel>()
    fun submitLists(collection: ArrayList<MainModel>) {
        this.collection.clear()
        this.collection.addAll(collection)

        notifyDataSetChanged()
    }
    private val collections = ArrayList<Movie>()
    fun submitList(collections:ArrayList<Movie>) {
        this.collections.clear()
        this.collections.addAll(collections)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val view :View
         view=   LayoutInflater.from(parent.context).inflate(R.layout.item_ludshie_films, parent, false)
        return CollectionViewHolder(view)

    }

    override fun getItemCount():Int{
        return collectiones.size

    }

    override fun getItemViewType(position: Int): Int {
        return collection3.size
        return collection.size
        return collections.size
    }


    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val collectiones = collectiones[position]
        val collection3 = collection3[position]
        val collection = collection[position]
        val collections = collections[position]




            holder.tvGenreMovie2.text = collections.title
            holder.rvMovieChild2.adapter = MovieAdapter2(collections.moviePopular)




            holder.tvGenreMovie.text = collection.title
        adapter=MovieAdapter(context,collection.movieModels)
            holder.rvMovieChild.adapter = adapter
        adapter.itemClick={
            onClick?.invoke(position)
        }




            holder.tvGenreMovie3.text = collection3.title
            holder.rvMovieChild3.adapter = MovieAdapter3(collection3.movieTopResult)




            holder.tvGenreMovie4.text = collectiones.title
        MovieAdapter4(collectiones.movieUpcoming).also { holder.rvMovieChild4.adapter = it }




    }

    inner class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGenreMovie = itemView.findViewById<TextView>(R.id.tvGenreMovie)
        val tvGenreMovie2 = itemView.findViewById<TextView>(R.id.tvGenreMovie2)
        val tvGenreMovie3 = itemView.findViewById<TextView>(R.id.tvGenreMovie3)
        val llbig = itemView.findViewById<LinearLayout>(R.id.ll_item_movie_mini)
        val image= itemView.findViewById<ImageView>(R.id.iv_movie_image)
        val tvVse = itemView.findViewById<TextView>(R.id.tv_vse_ludshie_films)
        val tvGenreMovie4 = itemView.findViewById<TextView>(R.id.tvGenreMovie4)
        val rvMovieChild = itemView.findViewById<RecyclerView>(R.id.rvMovieChild)
        val rvMovieChild2 = itemView.findViewById<RecyclerView>(R.id.rvMovieChild2)
        val rvMovieChild3 = itemView.findViewById<RecyclerView>(R.id.rvMovieChild3)
        val rvMovieChild4 = itemView.findViewById<RecyclerView>(R.id.rvMovieChild4)



    }

}