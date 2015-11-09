package com.alpha0.popular_movies_app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by dan on 11/8/15.
 */
public class DetailsActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String TMDB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500/";
        setContentView(R.layout.details_main);

        Bundle bundle = getIntent().getExtras();
        Movie movie = bundle.getParcelable("Movie");
        ImageView iconView = (ImageView) findViewById(R.id.movie_image);
        Glide.with(this).load(TMDB_IMAGE_BASE_URL + movie.getImage()).placeholder(R.drawable.loader).into(iconView);
        //Log.e("MOVIE_DATA", movie.getMovieName());
        TextView textView = (TextView) findViewById(R.id.movie_title);
        textView.setText(movie.getMovieName());

        EditText editText = (EditText) findViewById(R.id.movie_synopsis);
        editText.setText(movie.getSynopsis());

        RatingBar stars = (RatingBar) findViewById(R.id.movie_rating);

        stars.setRating(Float.parseFloat(movie.getRating()));
    }
}
