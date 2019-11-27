package com.supernova.jetpack.movietron.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.supernova.jetpack.movietron.data.FakeDataSource;
import com.supernova.jetpack.movietron.data.source.local.LocalRepository;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.data.source.remote.RemoteRepository;
import com.supernova.jetpack.movietron.data.source.remote.response.MovieResponse;
import com.supernova.jetpack.movietron.data.source.remote.response.TvResponse;
import com.supernova.jetpack.movietron.util.InstantAppExecutors;
import com.supernova.jetpack.movietron.util.LiveDataTestUtil;
import com.supernova.jetpack.movietron.util.PagedListUtil;
import com.supernova.jetpack.movietron.vo.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class AppRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final LocalRepository local = Mockito.mock(LocalRepository.class);
    private final RemoteRepository remote = Mockito.mock(RemoteRepository.class);
    private final InstantAppExecutors instantAppExecutors = Mockito.mock(InstantAppExecutors.class);
    private final FakeAppRepository appRepository = new FakeAppRepository(local, remote, instantAppExecutors);

    private final List<MovieResponse> movieResponses = FakeDataSource.generateMovieResponse();
    private final int movieId = movieResponses.get(0).getId();

    private final List<TvResponse> tvResponses = FakeDataSource.generateTvResponse();
    private final int tvId = tvResponses.get(0).getId();

    @Test
    public void getMovies() {
        MutableLiveData<List<ItemEntity>> dummyMovies = new MutableLiveData<>();
        dummyMovies.setValue(FakeDataSource.generateMovieItems());

        when(local.getMovies()).thenReturn(dummyMovies);

        Resource<List<ItemEntity>> results = LiveDataTestUtil.getValue(appRepository.getMovies());

        verify(local).getMovies();
        assertNotNull(results.data);
        assertEquals(movieResponses.size(), results.data.size());
    }

    @Test
    public void getMovie() {
        MutableLiveData<ItemEntity> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(FakeDataSource.generateMovieItems().get(0));

        when(local.getMovie(movieId)).thenReturn(dummyMovie);

        Resource<ItemEntity> result = LiveDataTestUtil.getValue(appRepository.getMovie(movieId));

        verify(local).getMovie(movieId);

        assertNotNull(result.data);
        assertNotNull(result.data.getTitle());
        assertEquals(movieResponses.get(0).getTitle(), result.data.getTitle());
    }

    @Test
    public void getTvShows() {
        MutableLiveData<List<ItemEntity>> dummyTvShows = new MutableLiveData<>();
        dummyTvShows.setValue(FakeDataSource.generateTvItems());

        when(local.getTvShows()).thenReturn(dummyTvShows );

        Resource<List<ItemEntity>> results = LiveDataTestUtil.getValue(appRepository.getTvShows());

        verify(local).getTvShows();
        assertNotNull(results.data);
        assertEquals(tvResponses.size(), results.data.size());
    }

    @Test
    public void getTv() {
        MutableLiveData<ItemEntity> dummyTv = new MutableLiveData<>();
        dummyTv.setValue(FakeDataSource.generateTvItems().get(0));

        when(local.getTv(tvId)).thenReturn(dummyTv);

        Resource<ItemEntity> result = LiveDataTestUtil.getValue(appRepository.getTv(tvId));

        verify(local).getTv(tvId);

        assertNotNull(result.data);
        assertNotNull(result.data.getTitle());
        assertEquals(tvResponses.get(0).getName(), result.data.getTitle());
    }

    @Test
    public void getFavoritedMovies() {
        List<ItemEntity> movies = FakeDataSource.generateMovieItems();
        DataSource.Factory<Integer, ItemEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(local.getFavoritedMoviesAsPaged()).thenReturn(dataSourceFactory);
        appRepository.getFavoritedMoviesAsPaged();
        Resource<PagedList<ItemEntity>> results = Resource.success(PagedListUtil.mockPagedList(movies));

        verify(local).getFavoritedMoviesAsPaged();
        assertNotNull(results.data);
        assertEquals(movies.size(), results.data.size());
    }

    @Test
    public void getFavoritedTvShows() {
        List<ItemEntity> tvshows = FakeDataSource.generateTvItems();
        DataSource.Factory<Integer, ItemEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(local.getFavoritedTvShowsAsPaged()).thenReturn(dataSourceFactory);
        appRepository.getFavoritedTvShowsAsPaged();
        Resource<PagedList<ItemEntity>> results = Resource.success(PagedListUtil.mockPagedList(tvshows));

        verify(local).getFavoritedTvShowsAsPaged();
        assertNotNull(results.data);
        assertEquals(tvshows.size(), results.data.size());
    }
}