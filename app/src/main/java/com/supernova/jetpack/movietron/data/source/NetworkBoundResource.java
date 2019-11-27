package com.supernova.jetpack.movietron.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.supernova.jetpack.movietron.data.source.remote.ApiResponse;
import com.supernova.jetpack.movietron.util.AppExecutors;
import com.supernova.jetpack.movietron.vo.Resource;

@SuppressWarnings({"RedundantTypeArguments", "WeakerAccess", "Convert2Lambda"})
public abstract class NetworkBoundResource<ResultType, RequestType> {
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    private final AppExecutors appExecutors;

    @SuppressWarnings("EmptyMethod")
    protected void onFetchFailed() {

    }

    protected abstract LiveData<ResultType> loadFromDB();

    protected abstract Boolean shouldFetch(ResultType data);

    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    protected abstract void saveCallResult(RequestType data);

    public NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        result.setValue(Resource.<ResultType>loading(null));

        final LiveData<ResultType> dbSource = loadFromDB();

        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(ResultType data) {
                result.removeSource(dbSource);
                if (shouldFetch(data)) {
                    fetchFromNetwork(dbSource);
                } else {
                    result.addSource(dbSource, new Observer<ResultType>() {
                        @Override
                        public void onChanged(ResultType newdata) {
                            result.setValue(Resource.success(newdata));
                        }
                    });
                }
            }
        });
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        final LiveData<ApiResponse<RequestType>> apiResponse = createCall();

        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(ResultType newdata) {
                result.setValue(Resource.loading(newdata));
            }
        });

        result.addSource(apiResponse, new Observer<ApiResponse<RequestType>>() {
            @Override
            public void onChanged(final ApiResponse<RequestType> response) {
                result.removeSource(apiResponse);
                result.removeSource(dbSource);

                switch (response.status) {
                    case SUCCESS:
                        appExecutors.diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                saveCallResult(response.body);
                                appExecutors.mainThread().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        result.addSource(loadFromDB(), new Observer<ResultType>() {
                                            @Override
                                            public void onChanged(ResultType newdata) {
                                                result.setValue(Resource.success(newdata));
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        break;
                    case EMPTY:
                        appExecutors.mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                result.addSource(loadFromDB(), new Observer<ResultType>() {
                                    @Override
                                    public void onChanged(ResultType newdata) {
                                        result.setValue(Resource.success(newdata));
                                    }
                                });
                            }
                        });
                        break;
                    case ERROR:
                        onFetchFailed();
                        result.addSource(dbSource, new Observer<ResultType>() {
                            @Override
                            public void onChanged(ResultType newdata) {
                                result.setValue(Resource.error(response.message, newdata));
                            }
                        });
                        break;
                }
            }
        });
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }
}
