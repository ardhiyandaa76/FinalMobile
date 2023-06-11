package com.example.finalmobile.adapters;

import com.example.finalmobile.models.MovieModel;

public interface OnFavoriteItemClickListener {
    void onItemClick(MovieModel movie);
    void onItemClickTv(TvModel tv);
}
}
