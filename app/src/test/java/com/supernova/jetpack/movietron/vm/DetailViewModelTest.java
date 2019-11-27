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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class DetailViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailViewModel viewModel;
    private final ItemEntity dummyMovie = FakeDataSource.generateMovieItems().get(0);
    private final ItemEntity dummyTv = FakeDataSource.generateTvItems().get(0);
    private final AppRepository appRepository = mock(AppRepository.class);
    private final int movieId = dummyMovie.getId();
    private final int tvId = dummyTv.getId();

    @Before
    public void setup() {
        viewModel = new DetailViewModel(appRepository);
    }

    @Test
    public void getMovieItem() {
        Resource<ItemEntity> resource = Resource.success(dummyMovie);
        MutableLiveData<Resource<ItemEntity>> movieResource = new MutableLiveData<>();
        movieResource.setValue(resource);

        when(appRepository.getMovie(movieId)).thenReturn(movieResource);

        Observer<Resource<ItemEntity>> observer = mock(Observer.class);
        viewModel.setParameter(ItemEntity.TYPE_MOVIE, movieId);
        viewModel.detail.observeForever(observer);

        verify(observer).onChanged(resource);
    }

    @Test
    public void getTvItem() {
        Resource<ItemEntity> resource = Resource.success(dummyTv);
        MutableLiveData<Resource<ItemEntity>> tvResource = new MutableLiveData<>();
        tvResource.setValue(resource);

        when(appRepository.getTv(tvId)).thenReturn(tvResource);

        Observer<Resource<ItemEntity>> observer = mock(Observer.class);
        viewModel.setParameter(ItemEntity.TYPE_TV, tvId);
        viewModel.detail.observeForever(observer);

        verify(observer).onChanged(resource);
    }
}