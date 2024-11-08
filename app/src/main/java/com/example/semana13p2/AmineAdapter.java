package com.example.semana13p2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AmineAdapter extends RecyclerView.Adapter<AmineAdapter.AnimeViewHolder> {

    private List<AnimeResponse.Anime> animeList;

    public AmineAdapter(List<AnimeResponse.Anime> animeList) {
        this.animeList = animeList;
    }

    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AnimeViewHolder holder, int position) {
        AnimeResponse.Anime anime = animeList.get(position);
        holder.titleTextView.setText(anime.getTitle());
        holder.synopsisTextView.setText(anime.getSynopsis());
    }

    @Override
    public int getItemCount() {
        return animeList != null ? animeList.size() : 0;
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView synopsisTextView;

        public AnimeViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(android.R.id.text1);
            synopsisTextView = itemView.findViewById(android.R.id.text2);
        }
    }

    public void setAnimeList(List<AnimeResponse.Anime> animeList) {
        this.animeList = animeList;
        notifyDataSetChanged();
    }
}
