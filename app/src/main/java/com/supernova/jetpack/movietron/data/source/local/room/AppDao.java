package com.supernova.jetpack.movietron.data.source.local.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;

import java.util.List;

@SuppressWarnings("UnusedReturnValue")
@Dao
public interface AppDao {

    @WorkerThread
    @Query("SELECT * FROM items WHERE type = 'movie'")
    LiveData<List<ItemEntity>> getMovies();

    @WorkerThread
    @Query("SELECT * FROM items WHERE type = 'tv'")
    LiveData<List<ItemEntity>> getTvShows();

    @Query("SELECT * FROM items WHERE type = 'movie' AND favorited = 1")
    DataSource.Factory<Integer, ItemEntity> getFavoritedMoviesAsPaged();

    @Query("SELECT * FROM items WHERE type = 'tv' AND favorited = 1")
    DataSource.Factory<Integer, ItemEntity> getFavoritedTvShowsAsPaged();

    @Query("SELECT * FROM items WHERE id = :itemId")
    LiveData<ItemEntity> getItemById(int itemId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertItems(List<ItemEntity> items);

    @Update
    int updateItem(ItemEntity item);

}
