package com.supernova.jetpack.movietron.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.supernova.jetpack.movietron.R;
import com.supernova.jetpack.movietron.adapter.FavoritePagedAdapter;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.model.Item;
import com.supernova.jetpack.movietron.vm.FavoriteViewModel;
import com.supernova.jetpack.movietron.vm.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings({"WeakerAccess", "CodeBlock2Expr"})
public class FavoriteTvFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoritePagedAdapter adapter;
    private FavoriteViewModel viewModel;

    public FavoriteTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_favorite_tv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            viewModel = obtainViewModel(getActivity());
            adapter = new FavoritePagedAdapter();

            adapter.setOnItemClickHandler(item -> {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_TYPE, Item.TYPE_MOVIE);
                intent.putExtra(DetailActivity.EXTRA_ID, item.getId());
                startActivity(intent);
            });

            adapter.setOnRemoveItemClickHandler((item, position) -> {
                viewModel.toggleFavorite(item);
            });

            viewModel.getTvShowsAsPaged().observe(this, resource -> {
                if (resource != null) {
                    switch (resource.status) {
                        case LOADING:
                            break;
                        case SUCCESS:
                            adapter.submitList(resource.data);
                            adapter.notifyDataSetChanged();
                            break;
                    }
                }
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);

            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
    }

    @NonNull
    private static FavoriteViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FavoriteViewModel.class);
    }

    private final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (getView() != null) {
                int swipedPosition = viewHolder.getAdapterPosition();
                ItemEntity item = adapter.getItemAt(swipedPosition);
                viewModel.toggleFavorite(item);
                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> viewModel.toggleFavorite(item));
                snackbar.show();
            }
        }
    });

}
