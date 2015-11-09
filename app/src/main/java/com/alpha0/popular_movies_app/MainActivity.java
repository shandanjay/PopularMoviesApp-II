package com.alpha0.popular_movies_app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String sort = "popularity.desc";

        Bundle bundle = new Bundle();
        bundle.putString("sort", sort);
        android.support.v4.app.Fragment frg = new MainActivityFragment();
        final android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        frg.setArguments(bundle);
        ft.replace(R.id.fragment, frg).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        String sort = "";

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort_rating) {
            sort = "vote_average.desc";

        }

        if(id == R.id.action_sort_popularity){
            sort = "popularity.desc";

        }

        if(id == R.id.action_sort_release_date){
            sort = "release_date.desc";

        }


        Bundle bundle = new Bundle();
        bundle.putString("sort", sort);
        android.support.v4.app.Fragment frg = new MainActivityFragment();
        final android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        frg.setArguments(bundle);
        ft.replace(R.id.fragment, frg).commit();



        return  true;

        //return super.onOptionsItemSelected(item);
    }
}
