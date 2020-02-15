package com.themovie.themoviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {


    TextView tv_aboutmovie,tv_moviename,tv_movierating,tv_movieduration,tv_releasedate,tv_language,tv_geners,tv_casts;
    ImageView iv_movieimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().hide();



        tv_moviename=(TextView)findViewById(R.id.tv_moviename);
        tv_movierating=(TextView)findViewById(R.id.tv_movierating);
        tv_movieduration=(TextView)findViewById(R.id.tv_movieduration);
        tv_releasedate=(TextView)findViewById(R.id.tv_releasedate);
        tv_language=(TextView)findViewById(R.id.tv_language);
        tv_geners=(TextView)findViewById(R.id.tv_geners);
        tv_casts=(TextView)findViewById(R.id.tv_casts);
        tv_aboutmovie=(TextView)findViewById(R.id.tv_aboutmovie);

        iv_movieimage=(ImageView) findViewById(R.id.iv_movieimage);

        Intent intent = getIntent();
        String moviedetails = intent.getStringExtra("moviedetails");
        String movieimage = intent.getStringExtra("movieimage");

        String movie_name = intent.getStringExtra("movie_name");
        String movie_rating = intent.getStringExtra("movie_rating");
        String movie_duration = intent.getStringExtra("movie_duration");
        String movie_releasedate = intent.getStringExtra("movie_releasedate");
        String movie_language = intent.getStringExtra("movie_language");
        String movie_genres = intent.getStringExtra("movie_genres");
        String movie_cast = intent.getStringExtra("movie_cast");

//setting all the details to views


        tv_moviename.setText(movie_name);
        tv_movierating.setText(movie_rating);
        tv_movieduration.setText(movie_duration);
        tv_releasedate.setText(movie_releasedate);
        tv_language.setText(movie_language);
        tv_geners.setText(movie_genres);
        tv_casts.setText(movie_cast);



        tv_aboutmovie.setText("About :\n\n"+moviedetails);
        Picasso.get()
                .load(
                        "https://pellicular-rumbles.000webhostapp.com/themovie/movie_images/" + movieimage)
                .into(iv_movieimage);
    }
}
