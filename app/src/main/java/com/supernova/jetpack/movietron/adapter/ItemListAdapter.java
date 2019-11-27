package com.supernova.jetpack.movietron.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.supernova.jetpack.movietron.R;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ListViewHolder> {

    private OnItemClickHandler onItemClickHandler;
    private final ArrayList<ItemEntity> items = new ArrayList<>();

    public void setItems(List<ItemEntity> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void setOnItemClickHandler(OnItemClickHandler handler) {
        onItemClickHandler = handler;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ItemEntity item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressWarnings("Convert2Lambda")
    class ListViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgPoster;
        final TextView txtTitle;
        final TextView txtRelease;
        final TextView txtOverview;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_poster_item);
            txtTitle = itemView.findViewById(R.id.txt_title_item);
            txtRelease = itemView.findViewById(R.id.txt_release_item);
            txtOverview = itemView.findViewById(R.id.txt_overview_item);
        }

        void bind(final ItemEntity item) {
            final Context context = itemView.getContext();

            Glide.with(context)
                    .load(item.getPoster())
                    .apply(new RequestOptions().override(100, 150))
                    .into(imgPoster);

            txtTitle.setText(item.getTitle());
            txtRelease.setText(item.getRelease());
            txtOverview.setText(item.getOverview());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickHandler.onClick(item);
                }
            });
        }
    }

    public interface OnItemClickHandler {
        void onClick(ItemEntity item);
    }
}
