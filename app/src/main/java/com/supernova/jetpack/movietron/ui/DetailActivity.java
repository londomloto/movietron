package com.supernova.jetpack.movietron.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.supernova.jetpack.movietron.R;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.vm.DetailViewModel;
import com.supernova.jetpack.movietron.vm.ViewModelFactory;
import com.supernova.jetpack.movietron.vo.Resource;

@SuppressWarnings("Convert2Lambda")
public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_ID = "id";

    private DetailViewModel viewModel;
    private ProgressBar progressBar;
    private ConstraintLayout wrapperLayout;
    private FloatingActionButton fabFavorite;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String type = intent.getStringExtra(EXTRA_TYPE);
        int id = intent.getIntExtra(EXTRA_ID, 0);

        progressBar = findViewById(R.id.progress_detail);
        wrapperLayout = findViewById(R.id.wrapper_detail);
        fabFavorite = findViewById(R.id.fab_detail);

        viewModel = obtainViewModel(this);

        if (savedInstanceState == null) {
            viewModel.setParameter(type, id);
        }

        viewModel.detail.observe(this, new Observer<Resource<ItemEntity>>() {
            @Override
            public void onChanged(Resource<ItemEntity> res) {
                if (res != null) {
                    switch (res.status) {
                        case LOADING:
                            loadStart();
                            break;
                        case SUCCESS:
                            loadEnd();
                            if (res.data != null) {
                                renderItem(res.data);
                                handleFavIcon(res.data.isFavorited());
                            }
                            break;
                        case ERROR:
                            loadEnd();
                            break;
                    }
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(getString(type.equals("movie") ? R.string.movie_detail : R.string.tv_detail));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        AppBarLayout appBarLayout = findViewById(R.id.app_bar_detail);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }

                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showFavoriteMenu(true);
                } else if (isShow) {
                    isShow = false;
                    showFavoriteMenu(false);
                }
            }
        });

        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.toggleFavorite();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        this.menu = menu;
        showFavoriteMenu(false);
        viewModel.detail.observe(this, new Observer<Resource<ItemEntity>>() {
            @Override
            public void onChanged(Resource<ItemEntity> res) {
                if (res != null) {
                    switch (res.status) {
                        case LOADING:
                            break;
                        case SUCCESS:
                            if (res.data != null) {
                                boolean state = res.data.isFavorited();
                                handleFavIcon(state);
                            }
                            break;
                    }
                }
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            viewModel.toggleFavorite();
        }
        return super.onOptionsItemSelected(item);
    }

    private static DetailViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailViewModel.class);
    }

    private void renderItem(ItemEntity item) {
        TextView txtTitle = findViewById(R.id.txt_detail_title);
        txtTitle.setText(item.getTitle());

        TextView txtRelease = findViewById(R.id.txt_detail_release);
        txtRelease.setText(item.getRelease());

        TextView txtOverview = findViewById(R.id.txt_detail_overview);
        txtOverview.setText(item.getOverview());

        ImageView imgPoster = findViewById(R.id.img_detail_poster);
        ImageView imgBackdrop = findViewById(R.id.img_detail_backdrop);

        Glide.with(this)
                .load(item.getPoster())
                .into(imgPoster);

        Glide.with(this)
                .load(item.getBackdrop())
                .into(imgBackdrop);
    }

    private void loadStart() {
        progressBar.setVisibility(View.VISIBLE);
        wrapperLayout.setVisibility(View.INVISIBLE);
    }

    private void loadEnd() {
        progressBar.setVisibility(View.GONE);
        wrapperLayout.setVisibility(View.VISIBLE);
    }

    private void showFavoriteMenu(Boolean show) {
        if (menu == null) return;

        MenuItem menuItem = menu.findItem(R.id.action_favorite);
        menuItem.setVisible(show);
    }

    private void handleFavIcon(boolean state) {
        // BUG workaround
        // https://issuetracker.google.com/issues/111316656

        fabFavorite.hide();

        if (state) {
            fabFavorite.setImageResource(R.drawable.ic_favorite_white);
        } else {
            fabFavorite.setImageResource(R.drawable.ic_favorite_border_white);
        }

        fabFavorite.show();

        if (menu != null) {
            MenuItem menuItem = menu.findItem(R.id.action_favorite);

            if (state) {
                menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white));
            } else {
                menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white));
            }
        }
    }
}
