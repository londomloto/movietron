package com.supernova.jetpack.movietron.vm;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.supernova.jetpack.movietron.data.source.AppRepository;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.vo.Resource;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final AppRepository appRepository;
    private final MutableLiveData<String> movieAction = new MutableLiveData<>();
    private final MutableLiveData<String> tvAction = new MutableLiveData<>();

    public MainViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public final LiveData<Resource<List<ItemEntity>>> movies = Transformations.switchMap(movieAction, new Function<String, LiveData<Resource<List<ItemEntity>>>>() {
        @Override
        public LiveData<Resource<List<ItemEntity>>> apply(String input) {
            return appRepository.getMovies();
        }
    });

    public final LiveData<Resource<List<ItemEntity>>> tvshows = Transformations.switchMap(tvAction, new Function<String, LiveData<Resource<List<ItemEntity>>>>() {
        @Override
        public LiveData<Resource<List<ItemEntity>>> apply(String input) {
            return appRepository.getTvShows();
        }
    });

    public void setMovieAction(String value) {
        movieAction.setValue(value);
    }

    public void setTvAction(String value) {
        tvAction.setValue(value);
    }

}
