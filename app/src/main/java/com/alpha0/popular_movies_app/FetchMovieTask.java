package com.alpha0.popular_movies_app;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FetchMovieTask extends AsyncTask<String, Void, List<Movie>> {

    final String TMDB_API_KEY = "<API KEY>";

    private List<Movie> movies;
    private Context context;
    private ImageView imageView;
    public FetchMovieTask() {
        super();

    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        String sort = "";
        if(params.length==0) {
            sort = "popularity.desc"; //release_date//vote_average
        }else {
            sort = params[0];
        }



        HttpURLConnection connection = null;
        BufferedReader reader = null;

        String jsonMovieData = "";

        try{
            final String TMDB_API_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
            final String TMDB_API_SORT_BY_PARAM = "sort_by";
            final String TMDB_API_KEY_PARAM = "api_key";
//            final String TMDB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/";

            Uri movieUrl = Uri.parse(TMDB_API_BASE_URL).buildUpon()
                    .appendQueryParameter(TMDB_API_SORT_BY_PARAM, sort)
                    .appendQueryParameter(TMDB_API_KEY_PARAM,TMDB_API_KEY)
                    .build();

            URL url = new URL(movieUrl.toString()) ;
//            Log.v("MOVIE_DATA", movieUrl.toString());


            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream =  connection.getInputStream();

            reader = new BufferedReader( new InputStreamReader(stream, "UTF-8"), 8 );
            String data = null;
            StringBuilder sb = new StringBuilder();
            while ((data=reader.readLine())!=null){
                sb.append(data+"\n");
            }
            jsonMovieData = sb.toString();
            //Log.v("MOVIE_DATA", jsonMovieData);
            int i =0;
                List<Movie> list = new CopyOnWriteArrayList<>();
                JSONObject jObj = new JSONObject(jsonMovieData);
                JSONArray jResults = jObj.getJSONArray("results");
            for (i=0; i<jResults.length(); i++){
                JSONObject jMovObj = jResults.getJSONObject(i);
                Movie movie = new Movie(jMovObj.getString("id"), jMovObj.getString("original_title"), jMovObj.getString("backdrop_path"));
                movie.setRating(jMovObj.getString("vote_average"));
                movie.setSynopsis(jMovObj.getString("overview"));
               // Log.e("MOVIE_DATA", movie.getMovieName());
                list.add(movie);

            }
            return  list;



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        this.movies = movies;
    }

    public List<Movie> getMovies(){
        return this.movies;
    }
}
