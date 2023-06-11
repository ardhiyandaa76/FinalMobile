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
import com.example.finalmobile.adapters.OnMovieListener;
import com.example.finalmobile.adapters.TvRecyclerView;
import com.example.finalmobile.models.TvModel;
import com.example.finalmobile.viewmodels.TvListViewModel;

import java.util.List;


public class TvListFragment extends Fragment implements OnMovieListener {

    private RecyclerView recyclerView;
    private TvRecyclerView tvRecyclerAdapter;
    private TvListViewModel tvListViewModel;
    private boolean isPopular = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_list, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        SetupSearchView(view);

        recyclerView = view.findViewById(R.id.recyclerView);
        tvListViewModel = new ViewModelProvider(this).get(TvListViewModel.class);

        ConfigureRecyclerView();
        ObserveAnyChange();
        ObservePopular();
        tvListViewModel.searchTvPop(1);
        Log.v("Tagy", "ispop: " + isPopular);

        return view;
    }

    private void ObservePopular() {
        tvListViewModel.getPop().observe(getViewLifecycleOwner(), new Observer<List<TvModel>>() {
            @Override
            public void onChanged(List<TvModel> tvModels) {
                if (tvModels != null) {
                    for (TvModel tvModel : tvModels) {
                        tvRecyclerAdapter.setmTvs(tvModels);
                    }
                }
            }
        });
    }

    private void ObserveAnyChange() {
        tvListViewModel.getTvs().observe(getViewLifecycleOwner(), new Observer<List<TvModel>>() {
            @Override
            public void onChanged(List<TvModel> tvModels) {
                if (tvModels != null) {
                    for (TvModel tvModel : tvModels) {
                        tvRecyclerAdapter.setmTvs(tvModels);
                    }
                }
            }
        });
    }

    private void ConfigureRecyclerView() {
        tvRecyclerAdapter = new TvRecyclerView(this);
        recyclerView.setAdapter(tvRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)) {
                    tvListViewModel.searchNextpage();
                }
            }
        });
    }

    @Override
    public void onMovieClick(int position) {
        TvModel selectedMovie = tvRecyclerAdapter.getSelectedTvs(position);

        TvDetailFragment tvDetailFragment = new TvDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable("movie", selectedMovie);
        tvDetailFragment.setArguments(arguments);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, tvDetailFragment)
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
                tvListViewModel.searchTvApi(query, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}