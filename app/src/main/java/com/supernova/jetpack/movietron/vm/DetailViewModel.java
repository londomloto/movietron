package com.supernova.jetpack.movietron.vm;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.supernova.jetpack.movietron.data.source.AppRepository;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.vo.Resource;

public class DetailViewModel extends ViewModel {
    private final AppRepository appRepository;
    private final MutableLiveData<Parameter> param = new MutableLiveData<>();

    public final LiveData<Resource<ItemEntity>> detail = Transformations.switchMap(param, new Function<Parameter, LiveData<Resource<ItemEntity>>>() {
        @Override
        public LiveData<Resource<ItemEntity>> apply(Parameter input) {
            if (input.type.equals(ItemEntity.TYPE_MOVIE)) {
                return appRepository.getMovie(input.id);
            } else {
                return appRepository.getTv(input.id);
            }
        }
    });

    public DetailViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public void setParameter(String type, int id) {
        Parameter p = new Parameter(type, id);
        param.setValue(p);
    }

    public void toggleFavorite() {
        if (detail.getValue() != null) {
            ItemEntity e = detail.getValue().data;
            if (e != null) {
                final boolean state = !e.isFavorited();
                appRepository.setItemFavorite(e, state);
            }
        }
    }

    static class Parameter {
        final String type;
        final int id;

        Parameter(String type, int id) {
            this.type = type;
            this.id = id;
        }
    }

}
