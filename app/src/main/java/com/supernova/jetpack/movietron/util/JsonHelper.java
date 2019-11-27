package com.supernova.jetpack.movietron.util;

import android.app.Application;

import com.supernova.jetpack.movietron.data.source.remote.response.MovieResponse;
import com.supernova.jetpack.movietron.data.source.remote.response.TvResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class JsonHelper {
    private final Application application;

    public JsonHelper(Application application) {
        this.application = application;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String parsingFileToString(String filename) {
        try {
            InputStream is = application.getAssets().open(filename);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<MovieResponse> loadMovies() {
        ArrayList<MovieResponse> list = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(parsingFileToString("MovieResponse.json"));
            JSONArray items = responseObject.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                int id = item.getInt("id");
                String title = item.getString("title");
                String overview = item.getString("overview");
                String release = item.getString("release_date");
                String poster = item.getString("poster_path");
                String backdrop = item.getString("backdrop_path");

                MovieResponse itemResponse = new MovieResponse (id, title, overview, release, poster, backdrop);
                list.add(itemResponse);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<TvResponse> loadTvShows() {
        ArrayList<TvResponse> list = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(parsingFileToString("TvResponse.json"));
            JSONArray items = responseObject.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                int id = item.getInt("id");
                String title = item.getString("name");
                String overview = item.getString("overview");
                String release = item.getString("first_air_date");
                String poster = item.getString("poster_path");
                String backdrop = item.getString("backdrop_path");

                TvResponse itemResponse = new TvResponse (id, title, overview, release, poster, backdrop);
                list.add(itemResponse);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
