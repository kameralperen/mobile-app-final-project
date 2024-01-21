package com.example.denemefinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.denemefinal.R;
import com.example.denemefinal.models.GalleryModel;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ItemHolder> {
    private ArrayList<GalleryModel> cards;

    private Context context;

    public GalleryAdapter( Context context, ArrayList<GalleryModel> cards) {
        this.cards = cards;
        this.context = context;
    }

    @NonNull
    @Override
    public GalleryAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ItemHolder holder, int position) {
        position = holder.getAdapterPosition();

        Glide.with(context).load(cards.get(position).getImage()).into(holder.cardImage);
        holder.username.setText(cards.get(position).getUsername());
        holder.label.setText(cards.get(position).getLabel());
        holder.description.setText(cards.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        TextView username, description, label;

        ImageView cardImage;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.gc_username);
            description = itemView.findViewById(R.id.gc_desc);
            label = itemView.findViewById(R.id.gc_label);
            cardImage = itemView.findViewById(R.id.gc_img);
        }
    }
}
