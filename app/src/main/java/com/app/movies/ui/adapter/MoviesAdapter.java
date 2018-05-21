package com.app.movies.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.movies.R;
import com.app.movies.ui.mapper.MovieViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<MovieViewModel> movieViewModels;

    public void addMovies(List<MovieViewModel> movieViewModels) {
        if (this.movieViewModels == null) {
            this.movieViewModels = movieViewModels;
        } else {
            this.movieViewModels.addAll(movieViewModels);
        }
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

        @BindView(R.id.movie_image)
        ImageView image;
        @BindView(R.id.movie_title)
        TextView title;
        @BindView(R.id.movie_year)
        TextView year;
        @BindView(R.id.movie_overview)
        TextView overview;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(MovieViewModel movieViewModel) {
            Picasso.with(itemView.getContext())
                    .load(movieViewModel.getImage())
                    .fit()
                    .into(image);
            title.setText(movieViewModel.getTitle());
            year.setText(movieViewModel.getYear());
            overview.setText(movieViewModel.getOverview());
        }
    }
}
