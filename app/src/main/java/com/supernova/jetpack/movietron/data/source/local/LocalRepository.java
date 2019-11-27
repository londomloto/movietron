package com.supernova.jetpack.movietron.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.data.source.local.room.AppDao;

import java.util.List;

@SuppressWarnings("unused")
public class LocalRepository {

    private static LocalRepository INSTANCE;
    private final AppDao appDao;

    private LocalRepository(AppDao appDao) {
        this.appDao = appDao;
    }

    public static LocalRepository getInstance(AppDao appDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(appDao);
        }
        return INSTANCE;
    }

    public LiveData<List<ItemEntity>> getMovies() {
        return appDao.getMovies();
    }

    public LiveData<List<ItemEntity>> getTvShows() {
        return appDao.getTvShows();
    }

    public LiveData<ItemEntity> getMovie(int id) {
        return appDao.getItemById(id);
    }

    public LiveData<ItemEntity> getTv(int id) {
        return appDao.getItemById(id);
    }

    public DataSource.Factory<Integer, ItemEntity> getFavoritedMoviesAsPaged() {
        return appDao.getFavoritedMoviesAsPaged();
    }

    public DataSource.Factory<Integer, ItemEntity> getFavoritedTvShowsAsPaged() {
        return appDao.getFavoritedTvShowsAsPaged();
    }

    public void insertItems(List<ItemEntity> items) {
        appDao.insertItems(items);
    }

    public void updateItem(ItemEntity item) {
        appDao.updateItem(item);
    }

    public void setItemFavorite(ItemEntity item, boolean state) {
        item.setFavorited(state);
        appDao.updateItem(item);
    }
}
