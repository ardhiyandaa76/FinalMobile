package com.example.finalmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalmobile.R;
import com.example.finalmobile.models.TvModel;
import com.example.finalmobile.utils.Credentials;

import java.util.List;

public class TvRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TvModel> mTvs;
    private OnMovieListener onMovieListener;

    private static final int DISPLAY_POP = 1;
    private static final int DISPLAY_SEARCH = 2;



    public TvRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        if (viewType == DISPLAY_SEARCH) {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,
                    parent, false);
            return new TvViewHolder(view, onMovieListener);
        }

        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_layout,
                    parent, false);
            return new Popular_view_holder(view, onMovieListener);
        }


    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        int itemViewType = getItemViewType(i);
        if (itemViewType == DISPLAY_SEARCH){
            ((TvViewHolder)holder).tvTitle.setText(mTvs.get(i).getName());
            ((TvViewHolder)holder).ratingBar.setRating((mTvs.get(i).getVote_average())/2);
            Glide.with(holder.itemView.getContext())
                    .load( "https://image.tmdb.org/t/p/w500/"
                            + mTvs.get(i).getPoster_path())
                    .into(((TvViewHolder)holder).imageView);

        }else{
            ((Popular_view_holder)holder).ratingBar_pop.setRating(mTvs.get(i).getVote_average());

            Glide.with(holder.itemView.getContext())
                    .load( "https://image.tmdb.org/t/p/w500/"
                            + mTvs.get(i).getPoster_path())
                    .into(((Popular_view_holder)holder).imageView_pop);

        }



    }

    @Override
    public int getItemCount() {
        if (mTvs != null){
            return mTvs.size();
        }
        return 0;
    }


    public void setmTvs(List<TvModel> mTvs) {
        this.mTvs = mTvs;
        notifyDataSetChanged();
    }


    public TvModel getSelectedTvs(int position){
        if (mTvs != null){
            if (mTvs.size() > 0){
                return mTvs.get(position);
            }
        }
        return  null;
    }


    @Override
    public int getItemViewType(int position) {

        if (Credentials.POPULAR){
            return DISPLAY_POP;
        }
        else
            return DISPLAY_SEARCH;
    }

}
