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
import com.example.finalmobile.db.DatabaseHelperTv;
import com.example.finalmobile.models.TvModel;

public class TvDetailFragment extends Fragment {
    private ImageView imageViewDetails, backButtonTvs;
    private TextView titleDetails, descDetails, releaseDate;
    private RatingBar ratingBarDetails;
    private ImageView favouriteImage;
    private boolean isFavorite = false;
    private OnFavoriteChangedListener onFavoriteChangedListener;
    private DatabaseHelperTv databaseHelper;
    private TvModel tvModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_detail, container, false);

        imageViewDetails = view.findViewById(R.id.imageView_details);
        titleDetails = view.findViewById(R.id.textView_title_details);
        descDetails = view.findViewById(R.id.textView_desc_details);
        ratingBarDetails = view.findViewById(R.id.ratingBar_details);
        releaseDate = view.findViewById(R.id.releaseDate);
        backButtonTvs = view.findViewById(R.id.backBtnTv);
        favouriteImage = view.findViewById(R.id.favouriteButton);

        databaseHelper = new DatabaseHelperTv(getActivity());

        getDataFromArguments();

        favouriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvModel != null) {
                    int tvId = tvModel.getTv_id();
                    String tvTitle = tvModel.getName();
                    if (isFavorite) {
                        removeFromFavorites(tvId);
                    } else {
                        addToFavorites(tvModel);
                    }
                    isFavorite = !isFavorite;
                    updateFavoriteImage();

                    if (onFavoriteChangedListener != null) {
                        onFavoriteChangedListener.onFavoriteChanged(tvTitle, isFavorite);
                    }
                }
            }
        });

        backButtonTvs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    private void getDataFromArguments() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("movie")) {
            tvModel = arguments.getParcelable("movie");
            if (tvModel != null) {
                titleDetails.setText(tvModel.getName());
                releaseDate.setText(tvModel.getFirst_air_date());
                descDetails.setText(tvModel.getMovie_overview());
                ratingBarDetails.setRating(tvModel.getVote_average() / 2);
                Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w500/" + tvModel.getPoster_path())
                        .into(imageViewDetails);
                isFavorite = databaseHelper.isFavorite(tvModel.getName());
                updateFavoriteImage();
            }
        }
    }

    private void addToFavorites(TvModel tv) {
        databaseHelper.addFavorite(tv);
    }

    private void removeFromFavorites(int tvId) {
        if (databaseHelper != null) {
            databaseHelper.removeFavoriteTv(tvId);
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
        void onFavoriteChanged(String tvTitle, boolean isFavorite);
    }

    public void setOnFavoriteChangedListener(OnFavoriteChangedListener listener) {
        onFavoriteChangedListener = listener;
    }
}


/* public class TvDetailFragment extends Fragment {
    private ImageView imageViewDetails;
    private TextView titleDetails, descDetails;
    private RatingBar ratingBarDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_detail, container, false);

        imageViewDetails = view.findViewById(R.id.imageView_details);
        titleDetails = view.findViewById(R.id.textView_title_details);
        descDetails = view.findViewById(R.id.textView_desc_details);
        ratingBarDetails = view.findViewById(R.id.ratingBar_details);

        GetDataFromArguments();

        return view;
    }
    private void GetDataFromArguments() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("movie")) {
            TvModel tvModel = arguments.getParcelable("movie");
            if (tvModel != null) {
                titleDetails.setText(tvModel.getName());
                descDetails.setText(tvModel.getMovie_overview());
                ratingBarDetails.setRating(tvModel.getVote_average() / 2);
                Log.v("Tagy", "X" + tvModel.getMovie_overview());
                Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w500/" + tvModel.getPoster_path())
                        .into(imageViewDetails);
            }
        }
    }
}
*/