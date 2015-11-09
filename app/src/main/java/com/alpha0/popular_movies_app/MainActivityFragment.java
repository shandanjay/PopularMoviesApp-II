package com.alpha0.popular_movies_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainActivityFragment extends Fragment {

    private MovieAdaptor movieAdaptor;

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MOVIE_DATA", "Destroying");
    }

    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_main, container, false);

       ImageView iconView = (ImageView) this.getActivity().findViewById(R.id.list_item_icon);

       FetchMovieTask task = new FetchMovieTask();
        String sort = "";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            sort = bundle.getString("sort", "popularity.desc");
        }

        try {
            movieAdaptor    = new MovieAdaptor(this.getActivity(), (List<Movie>) task.execute(sort).get() );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        gridView = (GridView) rootView.findViewById(R.id.gridview);


        gridView.setAdapter(movieAdaptor);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie1 = (Movie) parent.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra("Movie", movie1);
                startActivity(intent);
            }
        });

        return rootView;
    }



}

