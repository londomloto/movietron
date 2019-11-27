package com.supernova.jetpack.movietron.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.supernova.jetpack.movietron.R;
import com.supernova.jetpack.movietron.adapter.ItemListAdapter;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.model.Item;
import com.supernova.jetpack.movietron.vm.MainViewModel;
import com.supernova.jetpack.movietron.vm.ViewModelFactory;
import com.supernova.jetpack.movietron.vo.Resource;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings({"WeakerAccess", "Convert2Lambda"})
public class MovieFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_movie);
        progressBar = view.findViewById(R.id.progress_movie);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            MainViewModel viewModel = obtainViewModel(getActivity());

            final ItemListAdapter adapter = new ItemListAdapter();

            adapter.setOnItemClickHandler(new ItemListAdapter.OnItemClickHandler() {
                @Override
                public void onClick(ItemEntity item) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_TYPE, Item.TYPE_MOVIE);
                    intent.putExtra(DetailActivity.EXTRA_ID, item.getId());
                    startActivity(intent);
                }
            });

            if (savedInstanceState == null) {
                viewModel.setMovieAction("load");
            }

            viewModel.movies.observe(this, new Observer<Resource<List<ItemEntity>>>() {
                @Override
                public void onChanged(Resource<List<ItemEntity>> res) {
                    if (res != null) {
                        switch (res.status) {
                            case LOADING:
                                progressBar.setVisibility(View.VISIBLE);
                                break;
                            case SUCCESS:
                                progressBar.setVisibility(View.GONE);
                                adapter.setItems(res.data);
                                adapter.notifyDataSetChanged();
                                break;
                            case ERROR:
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), res.message, Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);

        }
    }

    @NonNull
    private static MainViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MainViewModel.class);
    }
}
