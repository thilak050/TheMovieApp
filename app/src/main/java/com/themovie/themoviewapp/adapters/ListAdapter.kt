package com.themovie.themoviewapp.adapters

import Mymovie
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.themovie.themoviewapp.MovieDetails
import com.themovie.themoviewapp.R




class ListAdapter(context:Context,var movielist: ArrayList<Mymovie>?) : RecyclerView.Adapter<ListAdapter.RecyclerViewHolder>() {

    var activity:Context = context


    override fun getItemCount(): Int {
        return movielist!!.size

    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textName: TextView = itemView.findViewById(R.id.tv_moviename)
        var textAddress: TextView = itemView.findViewById(R.id.tv_rating)
        var img_moviename: ImageView = itemView.findViewById(R.id.img_moviename)




        fun bindData(movielist: ArrayList<Mymovie>?, position: Int,context: Context) {
//bind the data to views in adapter


            textName.text = movielist!!.get(position).movie_name
            textAddress.text = movielist.get(position).movie_rating
            Picasso.get()
                .load(
                    "https://pellicular-rumbles.000webhostapp.com/themovie/movie_images/" + movielist.get(
                        position
                    ).movie_image
                )
                .placeholder(R.drawable.img_nomovieimage)
                .error(R.drawable.img_nomovieimage)
                .into(img_moviename);
            itemView.setOnClickListener(View.OnClickListener {

                val intent = Intent(context, MovieDetails::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                intent.putExtra("moviedetails", ""+movielist.get(position).movie_about)
                intent.putExtra("movieimage", ""+movielist.get(position).movie_image)
                intent.putExtra("movie_name", ""+movielist.get(position).movie_name)
                intent.putExtra("movie_rating", ""+movielist.get(position).movie_rating)
                intent.putExtra("movie_duration", ""+movielist.get(position).movie_duration)
                intent.putExtra("movie_releasedate", ""+movielist.get(position).movie_releasedate)
                intent.putExtra("movie_genres", ""+movielist.get(position).movie_genres)
                intent.putExtra("movie_cast", ""+movielist.get(position).movie_cast)
                intent.putExtra("movie_language", ""+movielist.get(position).movie_language)
                context.startActivity(intent)

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindData(movielist, position,activity)    }


}


