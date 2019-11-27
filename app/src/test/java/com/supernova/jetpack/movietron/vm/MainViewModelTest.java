package com.supernova.jetpack.movietron.vm;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.supernova.jetpack.movietron.data.FakeDataSource;
import com.supernova.jetpack.movietron.data.source.AppRepository;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MainViewModel viewModel;
    private final AppRepository appRepository = mock(AppRepository.class);

    @Before
    public void setup() {
        viewModel = new MainViewModel(appRepository);
    }

    @Test
    public void getMovies() {
        Resource<List<ItemEntity>> resource = Resource.success(FakeDataSource.generateMovieItems());
        MutableLiveData<Resource<List<ItemEntity>>> dummyMovies = new MutableLiveData<>();
        dummyMovies.setValue(resource);

        when(appRepository.getMovies()).thenReturn(dummyMovies);

        Observer<Resource<List<ItemEntity>>> observer = mock(Observer.class);

        viewModel.setMovieAction("load");
        viewModel.movies.observeForever(observer);

        verify(observer).onChanged(resource);
    }

    @Test
    public void getTvShows() {
        Resource<List<ItemEntity>> resource = Resource.success(FakeDataSource.generateTvItems());
        MutableLiveData<Resource<List<ItemEntity>>> dummyTvshows = new MutableLiveData<>();
        dummyTvshows.setValue(resource);

        when(appRepository.getTvShows()).thenReturn(dummyTvshows);

        Observer<Resource<List<ItemEntity>>> observer = mock(Observer.class);

        viewModel.setTvAction("load");
        viewModel.tvshows.observeForever(observer);

        verify(observer).onChanged(resource);
    }

}