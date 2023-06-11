package com.example.finalmobile.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalmobile.R;
import com.example.finalmobile.adapters.FavoriteAdapter;
import com.example.finalmobile.adapters.FavoriteAdapterTv;
import com.example.finalmobile.db.DatabaseHelper;
import com.example.finalmobile.db.DatabaseHelperTv;
import com.example.finalmobile.models.MovieModel;
import com.example.finalmobile.models.TvModel;

import java.util.List;

public class FavouriteListFragment extends Fragment implements FavoriteAdapter.OnFavoriteItemClickListener, FavoriteAdapterTv.OnFavoriteItemClickListener {
    private RecyclerView recyclerViewMovies, recyclerViewTvShows;
    private FavoriteAdapter favoriteAdapterMovies;
    private FavoriteAdapterTv favoriteAdapterTvShows;
    private List<MovieModel> favoriteMovies;
    private List<TvModel> favoriteTvShows;
    private DatabaseHelper databaseHelper;
    private DatabaseHelperTv databaseHelperTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_list, container, false);

        recyclerViewMovies = view.findViewById(R.id.recyclerViewFavorites);
        recyclerViewTvShows = view.findViewById(R.id.recyclerViewFavoritesTvs);

        // Set layout manager and adapter for movie favorites
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        favoriteAdapterMovies = new FavoriteAdapter();
        favoriteAdapterMovies.setOnFavoriteItemClickListener(this);
        recyclerViewMovies.setAdapter(favoriteAdapterMovies);

        // Set layout manager and adapter for TV show favorites
        recyclerViewTvShows.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        favoriteAdapterTvShows = new FavoriteAdapterTv();
        favoriteAdapterTvShows.setOnFavoriteItemClickListener(this);
        recyclerViewTvShows.setAdapter(favoriteAdapterTvShows);

        databaseHelper = new DatabaseHelper(getActivity());
        favoriteMovies = databaseHelper.getAllFavoriteMovies();
        favoriteAdapterMovies.setFavoritesList(favoriteMovies);

        for (MovieModel movie : favoriteMovies) {
            Log.d("FavouriteListFragment", "Movie: " + movie.getTitle());
        }

        databaseHelperTv = new DatabaseHelperTv(getActivity());
        favoriteTvShows = databaseHelperTv.getAllFavoriteTvs();
        favoriteAdapterTvShows.setFavoritesList(favoriteTvShows);

        for (TvModel tvShow : favoriteTvShows) {
            Log.d("FavouriteListFragment", "TV Show: " + tvShow.getName());
        }

        return view;
    }

    @Override
    public void onItemClick(MovieModel movie) {
        // Buka MovieDetailFragment dengan mengirim data film yang dipilih
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);
        movieDetailFragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, movieDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onItemClickTv(TvModel tvShow) {
        TvDetailFragment tvDetailFragment = new TvDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", tvShow);
        tvDetailFragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, tvDetailFragment)
                .addToBackStack(null)
                .commit();
    }
}
