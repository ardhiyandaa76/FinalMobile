package com.example.finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public ImageView movieBtn, tvBtn, favBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initiateViews();

        FragmentManager fragmentManager = getSupportFragmentManager();
        MovieListFragment movieListFragment = new MovieListFragment();
        Fragment fragment =
                fragmentManager.findFragmentByTag(MovieListFragment.class.getSimpleName());
        if(!(fragment instanceof MovieListFragment)){
            fragmentManager.beginTransaction().add(R.id.frame_layout, movieListFragment
            ,MovieListFragment.class.getSimpleName()).commit();
        }

        movieBtn.setOnClickListener(v -> {
            Fragment fragment1 =
                    fragmentManager.findFragmentByTag(MovieListFragment.class.getSimpleName());
            if(!(fragment1 instanceof MovieListFragment)){
                fragmentManager.beginTransaction().replace(R.id.frame_layout, movieListFragment
                        ,MovieListFragment.class.getSimpleName()).commit();
            }
        });

        tvBtn.setOnClickListener(v -> {
            Fragment fragment1 =
                    fragmentManager.findFragmentByTag(TvListFragment.class.getSimpleName());
            if(!(fragment1 instanceof TvListFragment)){
                fragmentManager.beginTransaction().replace(R.id.frame_layout, new TvListFragment()
                        ,TvListFragment.class.getSimpleName()).commit();
            }
        });

    }


    private void initiateViews(){
        movieBtn = findViewById(R.id.movieBtn);
        tvBtn = findViewById(R.id.tvBtn);
        favBtn = findViewById(R.id.favouriteBtn);
    }
}