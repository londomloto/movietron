package com.supernova.jetpack.movietron.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.supernova.jetpack.movietron.data.source.AppRepository;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.vo.Resource;

public class FavoriteViewModel extends ViewModel {
    private final AppRepository appRepository;

    public FavoriteViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public LiveData<Resource<PagedList<ItemEntity>>> getMoviesPaged() {
        return appRepository.getFavoritedMoviesAsPaged();
    }

    public LiveData<Resource<PagedList<ItemEntity>>> getTvShowsAsPaged() {
        return appRepository.getFavoritedTvShowsAsPaged();
    }

    public void toggleFavorite(ItemEntity item) {
        final boolean state = !item.isFavorited();
        appRepository.setItemFavorite(item, state);
    }
}
