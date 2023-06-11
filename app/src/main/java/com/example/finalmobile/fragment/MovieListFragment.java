package com.example.finalmobile.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalmobile.R;
import com.example.finalmobile.adapters.MovieRecyclerView;
import com.example.finalmobile.adapters.OnMovieListener;
import com.example.finalmobile.models.MovieModel;
import com.example.finalmobile.viewmodels.MovieListViewModel;

import java.util.List;


public class MovieListFragment extends Fragment implements OnMovieListener {

    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerAdapter;
    private MovieListViewModel movieListViewModel;
    private boolean isPopular = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        SetupSearchView(view);

        recyclerView = view.findViewById(R.id.recyclerView);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ConfigureRecyclerView();
        ObserveAnyChange();
        ObservePopular();
        movieListViewModel.searchMoviePop(1);
        Log.v("Tagy", "ispop: " + isPopular);

        return view;
    }

    private void ObservePopular() {
        movieListViewModel.getPop().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null) {
                    for (MovieModel movieModel : movieModels) {
                        movieRecyclerAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }

    private void ObserveAnyChange() {
        movieListViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null) {
                    for (MovieModel movieModel : movieModels) {
                        movieRecyclerAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }

    private void ConfigureRecyclerView() {
        movieRecyclerAdapter = new MovieRecyclerView(this);
        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)) {
                    movieListViewModel.searchNextpage();
                }
            }
        });
    }

    @Override
    public void onMovieClick(int position) {
        MovieModel selectedMovie = movieRecyclerAdapter.getSelectedMovie(position);

        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable("movie", selectedMovie);
        movieDetailFragment.setArguments(arguments);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, movieDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCategoryClick(String category) {
    }

    private void SetupSearchView(View view) {
        final SearchView searchView = view.findViewById(R.id.search_view);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPopular = false;
                Log.v("Tag", "is pop: " + isPopular);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(query, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
