package com.supernova.jetpack.movietron.vm;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.supernova.jetpack.movietron.data.source.AppRepository;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class FavoriteViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private FavoriteViewModel viewModel;
    private final AppRepository appRepository = mock(AppRepository.class);

    @Before
    public void setup() {
        viewModel = new FavoriteViewModel(appRepository);
    }

    @Test
    public void getMovies() {

        MutableLiveData<Resource<PagedList<ItemEntity>>> dummyMovies = new MutableLiveData<>();
        PagedList<ItemEntity> pagedList = mock(PagedList.class);

        dummyMovies.setValue(Resource.success(pagedList));

        when(appRepository.getFavoritedMoviesAsPaged()).thenReturn(dummyMovies);

        Observer<Resource<PagedList<ItemEntity>>> observer = mock(Observer.class);

        viewModel.getMoviesPaged().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
    }

    @Test
    public void getTvShows() {
        MutableLiveData<Resource<PagedList<ItemEntity>>> dummyTvShows = new MutableLiveData<>();
        PagedList<ItemEntity> pagedList = mock(PagedList.class);

        dummyTvShows.setValue(Resource.success(pagedList));

        when(appRepository.getFavoritedTvShowsAsPaged()).thenReturn(dummyTvShows);

        Observer<Resource<PagedList<ItemEntity>>> observer = mock(Observer.class);

        viewModel.getTvShowsAsPaged().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
    }
}