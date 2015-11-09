package com.alpha0.popular_movies_app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dan on 11/6/15.
 */

public class Movie implements Parcelable{

    String movieId;

    String movieName;

    String image;

    String rating;

    String synopsis;

    List<Movie> movies;


    protected Movie(Parcel in) {
        movieId = in.readString();
        movieName = in.readString();
        image = in.readString();
        rating = in.readString();
        synopsis = in.readString();
        this.movies = in.readArrayList(Movie.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getImage() {
        return image;
    }

    public Movie(String mId, String mName, String image)
    {
        this.movieId = mId;
        this.movieName = mName;
        this.image = image;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getRating() {
        return rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieId);
        dest.writeString(movieName);
        dest.writeString(image);
        dest.writeString(rating);
        dest.writeString(synopsis);
    }
}