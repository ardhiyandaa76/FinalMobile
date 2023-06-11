package com.example.finalmobile.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalmobile.R;

public class TvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView imageView;

    TextView tvTitle, releaseDate;
    RatingBar ratingBar;
    OnMovieListener onMovieListener;
    public TvViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);

        this.onMovieListener = onMovieListener;
        tvTitle = itemView.findViewById(R.id.titleMovie);
        releaseDate = itemView.findViewById(R.id.releaseDate);
        imageView = itemView.findViewById(R.id.movie_img);
        ratingBar = itemView.findViewById(R.id.rating_bar);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
