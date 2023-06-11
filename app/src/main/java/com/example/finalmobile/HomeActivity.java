package com.example.finalmobile;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.finalmobile.fragment.FavouriteListFragment;
import com.example.finalmobile.fragment.MovieListFragment;
import com.example.finalmobile.fragment.TvListFragment;

public class HomeActivity extends AppCompatActivity {
    ImageView movieButton, tvButton, favouriteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MovieListFragment movieListFragment = new MovieListFragment();
        TvListFragment tvListFragment = new TvListFragment();
        FavouriteListFragment  favouriteListFragment = new FavouriteListFragment();

        Fragment fragment =
                fragmentManager.findFragmentByTag(MovieListFragment.class.getSimpleName());
        if(!(fragment instanceof MovieListFragment)){
            fragmentManager.beginTransaction().add(R.id.frame_layout, movieListFragment
                    ,MovieListFragment.class.getSimpleName()).commit();
        }

        movieButton = findViewById(R.id.movieBtn);
        tvButton = findViewById(R.id.tvBtn);
        favouriteButton = findViewById(R.id.favBtn);

        movieButton.setOnClickListener(v -> {
            if(!(fragment instanceof MovieListFragment)){
                fragmentManager.beginTransaction().replace(R.id.frame_layout, movieListFragment
                        ,MovieListFragment.class.getSimpleName()).commit();
            }
        });
        tvButton.setOnClickListener(v -> {
            if(!(fragment instanceof TvListFragment)){
                fragmentManager.beginTransaction().replace(R.id.frame_layout, tvListFragment
                        ,TvListFragment.class.getSimpleName()).commit();
            }
        });
        favouriteButton.setOnClickListener(v -> {
            if(!(fragment instanceof FavouriteListFragment)){
                fragmentManager.beginTransaction().replace(R.id.frame_layout, favouriteListFragment
                        ,FavouriteListFragment.class.getSimpleName()).commit();
            }
        });



    }



}