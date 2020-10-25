package com.example.mvvm_0706011910025.ui.main.TvShow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_0706011910025.R;
import com.example.mvvm_0706011910025.adapter.TvShowAdapter;
import com.example.mvvm_0706011910025.model.TvShow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowFragment extends Fragment {

    @BindView(R.id.rv_tvShow)
    RecyclerView rv_tvShow;

    private TvShowViewModel viewModel;
    private TvShowAdapter adapter;

    public TvShowFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = new TvShowAdapter(getContext());

        rv_tvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_tvShow.setAdapter(adapter);

        viewModel = ViewModelProviders.of(requireActivity()).get(TvShowViewModel.class);
        viewModel.getTvShowCollection().observe(requireActivity(), observeViewModel);

//        Movie movie = new Movie();
//
//        btn_toDetail.setOnClickListener(view1 ->{
//            NavDirections action = MovieFragmentDirections.actionDetailFragment(movie);
//            Navigation.findNavController(view).navigate(action);
//        });
    }

    private Observer<List<TvShow>> observeViewModel = tvShows -> {
        if (tvShows != null){
            //set adapter
            adapter.setListTvShow(tvShows);
            adapter.notifyDataSetChanged();
//                rv_movie.setAdapter(adapter);
            //add adapter to recycler view
        }
    };

}