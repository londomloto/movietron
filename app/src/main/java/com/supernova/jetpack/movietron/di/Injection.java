package com.supernova.jetpack.movietron.di;

import android.app.Application;

import com.supernova.jetpack.movietron.data.source.AppRepository;
import com.supernova.jetpack.movietron.data.source.local.LocalRepository;
import com.supernova.jetpack.movietron.data.source.local.room.AppDatabase;
import com.supernova.jetpack.movietron.data.source.remote.RemoteRepository;
import com.supernova.jetpack.movietron.util.AppExecutors;
import com.supernova.jetpack.movietron.util.JsonHelper;

public class Injection {

    public static AppRepository provideRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        LocalRepository localRepository = LocalRepository.getInstance(database.appDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
        AppExecutors appExecutors = new AppExecutors();

        return AppRepository.getInstance(localRepository, remoteRepository, appExecutors);
    }

}
