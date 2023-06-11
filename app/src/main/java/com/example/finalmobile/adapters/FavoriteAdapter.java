package com.example.finalmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalmobile.R;
import com.example.finalmobile.models.MovieModel;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<MovieModel> favoritesList;
    private OnFavoriteItemClickListener onFavoriteItemClickListener;

    public interface OnFavoriteItemClickListener {
        void onItemClick(MovieModel movie);
    }

    public void setOnFavoriteItemClickListener(OnFavoriteItemClickListener listener) {
        onFavoriteItemClickListener = listener;
    }

    public void setFavoritesList(List<MovieModel> favoritesList) {
        this.favoritesList = favoritesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieModel movie = favoritesList.get(position);
        holder.textViewTitle.setText(movie.getTitle());
        holder.ratingBar.setRating(movie.getVote_average()/2);
        //use Glide
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path())
                .into(holder.imageViewPoster);
        // Set other relevant information to the views if needed
    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private ImageView imageViewPoster;
        private RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.titleMovie);
            imageViewPoster = itemView.findViewById(R.id.movie_img);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onFavoriteItemClickListener != null) {
                        MovieModel movie = favoritesList.get(position);
                        onFavoriteItemClickListener.onItemClick(movie);
                    }
                }
            });
        }
    }
}
