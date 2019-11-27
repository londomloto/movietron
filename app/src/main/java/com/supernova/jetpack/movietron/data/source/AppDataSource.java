package com.supernova.jetpack.movietron.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.vo.Resource;

import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused"})
public interface AppDataSource {

    LiveData<Resource<List<ItemEntity>>> getMovies();

    LiveData<Resource<ItemEntity>> getMovie(int id);

    LiveData<Resource<List<ItemEntity>>> getTvShows();

    LiveData<Resource<ItemEntity>> getTv(int id);

    LiveData<Resource<PagedList<ItemEntity>>> getFavoritedMoviesAsPaged();

    LiveData<Resource<PagedList<ItemEntity>>> getFavoritedTvShowsAsPaged();

    void setItemFavorite(ItemEntity item, boolean state);

}
