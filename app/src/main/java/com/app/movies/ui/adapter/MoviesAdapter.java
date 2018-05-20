package com.app.movies.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.movies.R;
import com.app.movies.ui.viewModel.MovieViewModel;

import java.util.List;

import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<MovieViewModel> movieViewModels;

    public void setViewModels(List<MovieViewModel> movieViewModels) {
        this.movieViewModels = movieViewModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movieViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return movieViewModels != null ? movieViewModels.size() : 0;
    }


    static class MovieViewHolder extends RecyclerView.ViewHolder {


        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(MovieViewModel movieViewModel) {

        }
    }
}
