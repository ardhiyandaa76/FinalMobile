package com.example.finalmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.finalmobile.R;
import com.example.finalmobile.db.DatabaseHelper;
import com.example.finalmobile.models.MovieModel;

import java.util.List;

public class MovieDetailFragment extends Fragment {
    private ImageView imageViewDetails, favouriteImage;
    private TextView titleDetails, descDetails, releaseDate;
    private RatingBar ratingBarDetails;
    private boolean isFavorite = false;
    private OnFavoriteChangedListener onFavoriteChangedListener;
    private DatabaseHelper databaseHelper;
    private MovieModel movieModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        imageViewDetails = view.findViewById(R.id.imageView_details);
        titleDetails = view.findViewById(R.id.textView_title_details);
        descDetails = view.findViewById(R.id.textView_desc_details);
        ratingBarDetails = view.findViewById(R.id.ratingBar_details);
        releaseDate = view.findViewById(R.id.releaseDate);
        favouriteImage = view.findViewById(R.id.favouriteButton);

        databaseHelper = new DatabaseHelper(getActivity());
        List<MovieModel> movieModels = databaseHelper.getAllFavoriteMovies();
        for (MovieModel movieModel : movieModels) {
            System.out.println("Movie" + movieModel.getMovie_id());
        }
        getDataFromArguments();
        favouriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieModel != null) {
                    int movieId = movieModel.getMovie_id();
                    String movieTitle = movieModel.getTitle();
                    if (isFavorite) {
                        removeFromFavorites(movieId); // Gunakan movieId sebagai parameter
                    } else {
                        addToFavorites(movieModel);
                    }
                    isFavorite = !isFavorite;
                    updateFavoriteImage();

                    if (onFavoriteChangedListener != null) {
                        onFavoriteChangedListener.onFavoriteChanged(movieTitle, isFavorite);
                    }
                }
            }
        });

        return view;
    }

    private void getDataFromArguments() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("movie")) {
            movieModel = arguments.getParcelable("movie");
            if (movieModel != null) {
                titleDetails.setText(movieModel.getTitle());
                releaseDate.setText(movieModel.getRelease_date());
                descDetails.setText(movieModel.getMovie_overview());
                ratingBarDetails.setRating(movieModel.getVote_average() / 2);
                Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w500/" + movieModel.getPoster_path())
                        .into(imageViewDetails);
                isFavorite = databaseHelper.isFavorite(movieModel.getTitle());
                updateFavoriteImage();
            }
        }
    }

    private void addToFavorites(MovieModel movie) {
        databaseHelper.addFavorite(movie);
    }

    private void removeFromFavorites(int movieId) {
        if (databaseHelper != null) {
            databaseHelper.removeFavoriteMovie(movieId);
        }
    }

    private void updateFavoriteImage() {
        if (isFavorite) {
            favouriteImage.setImageResource(R.drawable.baseline_favorite_24);
        } else {
            favouriteImage.setImageResource(R.drawable.baseline_favorite_border_24);
        }
    }

    public interface OnFavoriteChangedListener {
        void onFavoriteChanged(String movieTitle, boolean isFavorite);
    }

    public void setOnFavoriteChangedListener(OnFavoriteChangedListener listener) {
        onFavoriteChangedListener = listener;
    }
}
