package com.supernova.jetpack.movietron.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.supernova.jetpack.movietron.data.source.local.LocalRepository;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.data.source.remote.ApiResponse;
import com.supernova.jetpack.movietron.data.source.remote.RemoteRepository;
import com.supernova.jetpack.movietron.data.source.remote.response.MovieResponse;
import com.supernova.jetpack.movietron.data.source.remote.response.TvResponse;
import com.supernova.jetpack.movietron.util.AppExecutors;
import com.supernova.jetpack.movietron.vo.Resource;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "Convert2Lambda"})
public class AppRepository implements AppDataSource {
    private volatile static AppRepository INSTANCE = null;
    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;
    private final AppExecutors appExecutors;

    private AppRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository, AppExecutors appExecutors) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
    }

    public static AppRepository getInstance(LocalRepository localRepository, RemoteRepository remoteRepository, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (AppRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppRepository(localRepository, remoteRepository, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<ItemEntity>>> getMovies() {
        return new NetworkBoundResource<List<ItemEntity>, List<MovieResponse>>(appExecutors) {

            @Override
            protected LiveData<List<ItemEntity>> loadFromDB() {
                return localRepository.getMovies();
            }

            @Override
            protected Boolean shouldFetch(List<ItemEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return remoteRepository.getMoviesAsLiveData();
            }

            @Override
            protected void saveCallResult(List<MovieResponse> responses) {
                List<ItemEntity> items = new ArrayList<>();

                for (MovieResponse e: responses) {
                    items.add(new ItemEntity(e.getId(),
                            ItemEntity.TYPE_MOVIE,
                            e.getTitle(),
                            e.getOverview(),
                            e.getRelease_date(),
                            e.getPoster(),
                            e.getBackdrop()));
                }

                localRepository.insertItems(items);
            }
        }.asLiveData();

    }

    @Override
    public LiveData<Resource<ItemEntity>> getMovie(final int id) {
        return new NetworkBoundResource<ItemEntity, MovieResponse>(appExecutors) {

            @Override
            protected LiveData<ItemEntity> loadFromDB() {
                return localRepository.getMovie(id);
            }

            @Override
            protected Boolean shouldFetch(ItemEntity data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return remoteRepository.getMovieAsLiveData(id);
            }

            @Override
            protected void saveCallResult(MovieResponse response) {
                List<ItemEntity> items = new ArrayList<>();

                items.add(new ItemEntity(response.getId(),
                        ItemEntity.TYPE_MOVIE,
                        response.getTitle(),
                        response.getOverview(),
                        response.getRelease_date(),
                        response.getPoster(),
                        response.getBackdrop()));

                localRepository.insertItems(items);
            }
        }.asLiveData();

    }

    @Override
    public LiveData<Resource<ItemEntity>> getTv(final int id) {
        return new NetworkBoundResource<ItemEntity, TvResponse>(appExecutors) {

            @Override
            protected LiveData<ItemEntity> loadFromDB() {
                return localRepository.getTv(id);
            }

            @Override
            protected Boolean shouldFetch(ItemEntity data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiResponse<TvResponse>> createCall() {
                return remoteRepository.getTvAsLiveData(id);
            }

            @Override
            protected void saveCallResult(TvResponse response) {
                List<ItemEntity> items = new ArrayList<>();
                items.add(new ItemEntity(response.getId(),
                        ItemEntity.TYPE_TV,
                        response.getName(),
                        response.getOverview(),
                        response.getFirst_air_date(),
                        response.getPoster(),
                        response.getBackdrop()));
                localRepository.insertItems(items);
            }
        }.asLiveData();

    }

    @Override
    public LiveData<Resource<List<ItemEntity>>> getTvShows() {
        return new NetworkBoundResource<List<ItemEntity>, List<TvResponse>>(appExecutors) {

            @Override
            protected LiveData<List<ItemEntity>> loadFromDB() {
                return localRepository.getTvShows();
            }

            @Override
            protected Boolean shouldFetch(List<ItemEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TvResponse>>> createCall() {
                return remoteRepository.getTvShowsAsLiveData();
            }

            @Override
            protected void saveCallResult(List<TvResponse> responses) {
                List<ItemEntity> items = new ArrayList<>();

                for (TvResponse e: responses) {
                    items.add(new ItemEntity(e.getId(),
                            ItemEntity.TYPE_TV,
                            e.getName(),
                            e.getOverview(),
                            e.getFirst_air_date(),
                            e.getPoster(),
                            e.getBackdrop()));
                }

                localRepository.insertItems(items);
            }
        }.asLiveData();

    }

    @Override
    public LiveData<Resource<PagedList<ItemEntity>>> getFavoritedMoviesAsPaged() {
        return new NetworkBoundResource<PagedList<ItemEntity>, List<MovieResponse>>(appExecutors) {

            @Override
            protected LiveData<PagedList<ItemEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavoritedMoviesAsPaged(), 5).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<ItemEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<ItemEntity>>> getFavoritedTvShowsAsPaged() {
        return new NetworkBoundResource<PagedList<ItemEntity>, List<TvResponse>>(appExecutors) {

            @Override
            protected LiveData<PagedList<ItemEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavoritedTvShowsAsPaged(), 5).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<ItemEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TvResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TvResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public void setItemFavorite(final ItemEntity item, final boolean state) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                localRepository.setItemFavorite(item, state);
            }
        });
    }

}
