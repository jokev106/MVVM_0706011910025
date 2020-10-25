package com.example.mvvm_0706011910025.ui.main.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_0706011910025.R;
import com.example.mvvm_0706011910025.adapter.MovieAdapter;
import com.example.mvvm_0706011910025.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment {
    @BindView(R.id.progressBar)
    ProgressBar loading;

    @BindView(R.id.rv_movie)
    RecyclerView rv_movie;

    private MovieViewModel viewModel;
    private MovieAdapter adapter;

    public MovieFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        showLoading(true);
        adapter = new MovieAdapter(getContext());

        rv_movie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_movie.setAdapter(adapter);

        viewModel = ViewModelProviders.of(requireActivity()).get(MovieViewModel.class);
        viewModel.getMovieCollection().observe(requireActivity(), observeViewModel);
    }

    private Observer<List<Movie>> observeViewModel = movies -> {
        if (movies != null){
            //set adapter
                adapter.setListMovie(movies);
                adapter.notifyDataSetChanged();
                showLoading(false);
//                rv_movie.setAdapter(adapter);
            //add adapter to recycler view
        }
};
    private void showLoading(Boolean state) {
        if (state) {
            rv_movie.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        } else {
            rv_movie.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }
}