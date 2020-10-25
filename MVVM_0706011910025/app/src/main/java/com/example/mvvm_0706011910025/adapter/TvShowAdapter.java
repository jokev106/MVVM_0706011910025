package com.example.mvvm_0706011910025.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvm_0706011910025.R;
import com.example.mvvm_0706011910025.model.TvShow;
import com.example.mvvm_0706011910025.ui.main.TvShow.TvShowFragmentDirections;
import com.example.mvvm_0706011910025.model.TvShow;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.CardViewViewHolder>{

    private final Context context;
    private List<TvShow> listTvShow;
    private List<TvShow> getListTvShow() {
        return listTvShow;
    }
    public void setListTvShow(List<TvShow> listTvShow) {
        this.listTvShow = listTvShow;
    }
    public TvShowAdapter(Context context) {
        this.listTvShow = new ArrayList<TvShow>();
        this.context = context;
    }

    @NonNull
    @Override
    public TvShowAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TvShowAdapter.CardViewViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final TvShowAdapter.CardViewViewHolder holder, int position) {
        TvShow tvShow = getListTvShow().get(position);
        Glide.with(context).load(tvShow.getCover()).centerCrop().into(holder.img);
        holder.lbl_title.setText(tvShow.getTitle());
        holder.lbl_date.setText(tvShow.getReleaseDate());
        holder.lbl_vote.setText(tvShow.getVote());
        holder.itemView.setOnClickListener(view -> {
            TvShowFragmentDirections.ActionTvShowFragmentToDetailFragment action = TvShowFragmentDirections.actionTvShowFragmentToDetailFragment(null, tvShow);
            Navigation.findNavController(view).navigate(action);
        });
        Log.d("TvShow", tvShow.getPoster());
    }

    @Override
    public int getItemCount() {
        return getListTvShow().size();
    }


    class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView lbl_title, lbl_date, lbl_vote;

        CardViewViewHolder(View itemView) {
            super(itemView);
            lbl_title = itemView.findViewById(R.id.lbl_title);
            lbl_date = itemView.findViewById(R.id.lbl_date);
            lbl_vote = itemView.findViewById(R.id.txt_vote);
            img = itemView.findViewById(R.id.img_detail_poster);
        }
    }
}