package com.supernova.jetpack.movietron.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.supernova.jetpack.movietron.R;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;

public class FavoritePagedAdapter extends PagedListAdapter<ItemEntity, FavoritePagedAdapter.FavoriteViewHolder> {

    private FavoritePagedAdapter.OnItemClickHandler onItemClickHandler;
    private FavoritePagedAdapter.OnRemoveItemClickHandler onRemoveItemClickHandler;

    private final static DiffUtil.ItemCallback<ItemEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ItemEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull ItemEntity oldItem, @NonNull ItemEntity newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull ItemEntity oldItem, @NonNull ItemEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public FavoritePagedAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        ItemEntity item  = getItem(position);
        holder.bind(item, position);
    }

    public ItemEntity getItemAt(int position) {
        return getItem(position);
    }

    @SuppressWarnings("Convert2Lambda")
    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgPoster;
        final TextView txtTitle;
        final TextView txtRelease;
        final TextView txtOverview;
        final ImageButton btnRemove;

        FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_poster_fav);
            txtTitle = itemView.findViewById(R.id.txt_title_fav);
            txtRelease = itemView.findViewById(R.id.txt_release_fav);
            txtOverview = itemView.findViewById(R.id.txt_overview_fav);
            btnRemove = itemView.findViewById(R.id.btn_remove_fav);
        }

        void bind(final ItemEntity item, final int position) {
            final Context context = itemView.getContext();

            if (item != null) {
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

                btnRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onRemoveItemClickHandler.onClick(item, position);
                    }
                });
            }


        }
    }

    public void setOnItemClickHandler(FavoritePagedAdapter.OnItemClickHandler fn) {
        this.onItemClickHandler = fn;
    }

    public void setOnRemoveItemClickHandler(FavoritePagedAdapter.OnRemoveItemClickHandler fn) {
        this.onRemoveItemClickHandler = fn;
    }

    public interface OnItemClickHandler {
        void onClick(ItemEntity item);
    }

    public interface OnRemoveItemClickHandler {
        void onClick(ItemEntity item, int position);
    }
}
