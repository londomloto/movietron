package com.supernova.jetpack.movietron.api;

import com.supernova.jetpack.movietron.BuildConfig;
import com.supernova.jetpack.movietron.data.source.remote.response.DiscoverResponse;
import com.supernova.jetpack.movietron.data.source.remote.response.MovieResponse;
import com.supernova.jetpack.movietron.data.source.remote.response.TvResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class Tmdb {

    public static final String API_KEY = BuildConfig.TMDB_API_KEY;
    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static ApiInterface api;

    public static ApiInterface getApi() {
        if (api == null) {
            OkHttpClient client = new OkHttpClient.Builder().build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(ApiInterface.class);
        }

        return api;
    }

    public interface ApiInterface {
        @GET("discover/movie?language=en-US")
        Call<DiscoverResponse<MovieResponse>> discoverMovie(@Query("api_key") String apiKey);

        @GET("discover/tv?language=en-US")
        Call<DiscoverResponse<TvResponse>> discoverTv(@Query("api_key") String apiKey);

        @GET("movie/{id}?language=en-US")
        Call<MovieResponse> lookupMovie(@Path("id") int id, @Query("api_key") String apiKey);

        @GET("movie/{id}?language=en-US")
        Call<TvResponse> lookupTv(@Path("id") int id, @Query("api_key") String apiKey);
    }
}
