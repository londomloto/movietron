package com.supernova.jetpack.movietron.data.source.remote;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.supernova.jetpack.movietron.api.Tmdb;
import com.supernova.jetpack.movietron.data.source.remote.response.DiscoverResponse;
import com.supernova.jetpack.movietron.data.source.remote.response.MovieResponse;
import com.supernova.jetpack.movietron.data.source.remote.response.TvResponse;
import com.supernova.jetpack.movietron.util.EspressoIdlingResource;
import com.supernova.jetpack.movietron.util.JsonHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("unused")
public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteRepository(JsonHelper jsonHelper) {
    }

    public static RemoteRepository getInstance(JsonHelper jsonHelper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(jsonHelper);
        }
        return INSTANCE;
    }

    public void getMovies(final DiscoverHandler<MovieResponse> fn) {
        EspressoIdlingResource.increment();

        Callback<DiscoverResponse<MovieResponse>> callback = new Callback<DiscoverResponse<MovieResponse>>() {
            @Override
            public void onResponse(@NonNull Call<DiscoverResponse<MovieResponse>> call, @NonNull Response<DiscoverResponse<MovieResponse>> response) {
                if (response.isSuccessful()) {
                    DiscoverResponse<MovieResponse> discover = response.body();
                    if (discover != null) {
                        fn.onDataAvailable(discover.getResults());
                    } else {
                        fn.onDataNotAvailable();
                    }
                } else {
                    fn.onDataNotAvailable();
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<DiscoverResponse<MovieResponse>> call, @NonNull Throwable t) {
                fn.onDataNotAvailable();
                EspressoIdlingResource.decrement();
            }
        };

        Tmdb.getApi().discoverMovie(Tmdb.API_KEY).enqueue(callback);

        /*
        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fn.onDataAvailable(jsonHelper.loadMovies());
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);
        */
    }

    public LiveData<ApiResponse<List<MovieResponse>>> getMoviesAsLiveData() {
        EspressoIdlingResource.increment();

        final MutableLiveData<ApiResponse<List<MovieResponse>>> result = new MutableLiveData<>();
        final List<MovieResponse> empty = new ArrayList<>();

        Callback<DiscoverResponse<MovieResponse>> callback = new Callback<DiscoverResponse<MovieResponse>>() {
            @Override
            public void onResponse(@NonNull Call<DiscoverResponse<MovieResponse>> call, @NonNull Response<DiscoverResponse<MovieResponse>> response) {
                if (response.isSuccessful()) {
                    DiscoverResponse<MovieResponse> discover = response.body();
                    if (discover != null) {
                        result.setValue(ApiResponse.success(discover.getResults()));
                    } else {
                        result.setValue(ApiResponse.empty("", empty));
                    }
                } else {
                    String message = "";

                    try {
                        ResponseBody body = response.errorBody();
                        if (body != null) {
                            JSONObject json = new JSONObject(body.string());
                            message = json.getString("status_message");
                        }
                    } catch (Exception ex) {
                        message = ex.getMessage();
                    }

                    result.setValue(ApiResponse.error(message, empty));
                }

                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DiscoverResponse<MovieResponse>> call, @NonNull Throwable t) {
                result.setValue(ApiResponse.error(t.getMessage(), empty));
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement();
                }
            }
        };

        Tmdb.getApi().discoverMovie(Tmdb.API_KEY).enqueue(callback);

        return result;
    }

    public void getTvShows(final DiscoverHandler<TvResponse> fn) {
        EspressoIdlingResource.increment();

        Callback<DiscoverResponse<TvResponse>> callback = new Callback<DiscoverResponse<TvResponse>>() {
            @Override
            public void onResponse(@NonNull Call<DiscoverResponse<TvResponse>> call, @NonNull Response<DiscoverResponse<TvResponse>> response) {
                if (response.isSuccessful()) {
                    DiscoverResponse<TvResponse> discover = response.body();
                    if (discover != null) {
                        fn.onDataAvailable(discover.getResults());
                    } else {
                        fn.onDataNotAvailable();
                    }
                } else {
                    fn.onDataNotAvailable();
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<DiscoverResponse<TvResponse>> call, @NonNull Throwable t) {
                fn.onDataNotAvailable();
                EspressoIdlingResource.decrement();
            }
        };

        Tmdb.getApi().discoverTv(Tmdb.API_KEY).enqueue(callback);

        /*
        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fn.onDataAvailable(jsonHelper.loadTvShows());
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);
        */
    }

    public LiveData<ApiResponse<List<TvResponse>>> getTvShowsAsLiveData() {
        EspressoIdlingResource.increment();

        final MutableLiveData<ApiResponse<List<TvResponse>>> result = new MutableLiveData<>();
        final List<TvResponse> empty = new ArrayList<>();

        Callback<DiscoverResponse<TvResponse>> callback = new Callback<DiscoverResponse<TvResponse>>() {
            @Override
            public void onResponse(@NonNull Call<DiscoverResponse<TvResponse>> call, @NonNull Response<DiscoverResponse<TvResponse>> response) {
                if (response.isSuccessful()) {
                    DiscoverResponse<TvResponse> discover = response.body();
                    if (discover != null) {
                        result.setValue(ApiResponse.success(discover.getResults()));
                    } else {
                        result.setValue(ApiResponse.empty("", empty));
                    }
                } else {
                    String message = "";

                    try {
                        ResponseBody body = response.errorBody();
                        if (body != null) {
                            JSONObject json = new JSONObject(body.string());
                            message = json.getString("status_message");
                        }
                    } catch (Exception ex) {
                        message = ex.getMessage();
                    }

                    result.setValue(ApiResponse.error(message, empty));
                }

                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DiscoverResponse<TvResponse>> call, @NonNull Throwable t) {
                result.setValue(ApiResponse.error(t.getMessage(), empty));
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement();
                }
            }
        };

        Tmdb.getApi().discoverTv(Tmdb.API_KEY).enqueue(callback);

        return result;
    }

    public void getMovie(int id, final LookupHandler<MovieResponse> fn) {
        EspressoIdlingResource.increment();
        Callback<MovieResponse> callback = new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    fn.onDataAvailable(response.body());
                } else {
                    fn.onDataNotAvailable();
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                fn.onDataNotAvailable();
                EspressoIdlingResource.decrement();
            }
        };
        Tmdb.getApi().lookupMovie(id, Tmdb.API_KEY).enqueue(callback);
    }

    public LiveData<ApiResponse<MovieResponse>> getMovieAsLiveData(int id) {
        EspressoIdlingResource.increment();

        final MutableLiveData<ApiResponse<MovieResponse>> result = new MutableLiveData<>();
        final MovieResponse empty = new MovieResponse();

        Callback<MovieResponse> callback = new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    result.setValue(ApiResponse.success(response.body()));
                } else {
                    String message = "";

                    try {
                        ResponseBody body = response.errorBody();
                        if (body != null) {
                            JSONObject json = new JSONObject(body.string());
                            message = json.getString("status_message");
                        }
                    } catch (Exception ex) {
                        message = ex.getMessage();
                    }

                    result.setValue(ApiResponse.error(message, empty));
                }

                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                result.setValue(ApiResponse.error(t.getMessage(), empty));
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement();
                }
            }
        };

        Tmdb.getApi().lookupMovie(id, Tmdb.API_KEY).enqueue(callback);

        return result;
    }

    public void getTv(int id, final LookupHandler<TvResponse> fn) {
        EspressoIdlingResource.increment();
        Callback<TvResponse> callback = new Callback<TvResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvResponse> call, @NonNull Response<TvResponse> response) {
                if (response.isSuccessful()) {
                    fn.onDataAvailable(response.body());
                } else {
                    fn.onDataNotAvailable();
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(@NonNull Call<TvResponse> call, @NonNull Throwable t) {
                fn.onDataNotAvailable();
                EspressoIdlingResource.decrement();
            }
        };
        Tmdb.getApi().lookupTv(id, Tmdb.API_KEY).enqueue(callback);
    }

    public LiveData<ApiResponse<TvResponse>> getTvAsLiveData(int id) {
        EspressoIdlingResource.increment();

        final MutableLiveData<ApiResponse<TvResponse>> result = new MutableLiveData<>();
        final TvResponse empty = new TvResponse();

        Callback<TvResponse> callback = new Callback<TvResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvResponse> call, @NonNull Response<TvResponse> response) {
                if (response.isSuccessful()) {
                    result.setValue(ApiResponse.success(response.body()));
                } else {
                    String message = "";

                    try {
                        ResponseBody body = response.errorBody();
                        if (body != null) {
                            JSONObject json = new JSONObject(body.string());
                            message = json.getString("status_message");
                        }
                    } catch (Exception ex) {
                        message = ex.getMessage();
                    }

                    result.setValue(ApiResponse.error(message, empty));
                }
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvResponse> call, @NonNull Throwable t) {
                result.setValue(ApiResponse.error(t.getMessage(), empty));
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement();
                }
            }
        };
        Tmdb.getApi().lookupTv(id, Tmdb.API_KEY).enqueue(callback);

        return result;
    }

    public interface DiscoverHandler<T> {
        void onDataAvailable(List<T> responses);
        @SuppressWarnings("EmptyMethod")
        void onDataNotAvailable();
    }

    public interface LookupHandler<T> {
        void onDataAvailable(T response);
        @SuppressWarnings("EmptyMethod")
        void onDataNotAvailable();
    }

}
