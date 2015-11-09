package com.alpha0.popular_movies_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by dan on 11/6/15.
 */
public class MovieAdaptor extends ArrayAdapter<Movie> {
    private static final String LOG_TAG = MovieAdaptor.class.getSimpleName();

    public MovieAdaptor(Activity context, List<Movie> movies) {

        super(context, 0, movies);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Movie movie = getItem(position);
        final String TMDB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w342/";


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView versionNameView = (TextView) convertView.findViewById(R.id.list_item_movie_name);
        versionNameView.setText(movie.getMovieName());


        ImageView iconView = (ImageView) convertView.findViewById(R.id.list_item_icon);
        Glide.with(this.getContext()).load(TMDB_IMAGE_BASE_URL+movie.getImage()).placeholder(R.drawable.loader).into(iconView);

        return convertView;
    }
}
