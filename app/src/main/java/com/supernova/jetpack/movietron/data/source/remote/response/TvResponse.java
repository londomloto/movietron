package com.supernova.jetpack.movietron.data.source.remote.response;

import com.supernova.jetpack.movietron.api.Tmdb;

@SuppressWarnings("unused")
public class TvResponse {
    private int id;
    private String name;
    private String overview;
    private String first_air_date;
    private String poster_path;
    private String backdrop_path;

    public TvResponse() {
    }

    public TvResponse(int id, String name, String overview, String first_air_date, String poster_path, String backdrop_path) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.first_air_date = first_air_date;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getPoster() {
        return String.format("%sw154%s", Tmdb.BASE_IMAGE_URL, this.poster_path);
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getBackdrop() {
        return String.format("%sw780%s", Tmdb.BASE_IMAGE_URL, this.backdrop_path);
    }
}
