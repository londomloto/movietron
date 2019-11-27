package com.supernova.jetpack.movietron.data.source.remote.response;

import com.supernova.jetpack.movietron.api.Tmdb;

@SuppressWarnings("unused")
public class MovieResponse {
    private int id;
    private String title;
    private String overview;
    private String release_date;
    private String poster_path;
    private String backdrop_path;

    public MovieResponse() {
    }

    public MovieResponse(int id, String title, String overview, String release_date, String poster_path, String backdrop_path) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getPoster() {
        return String.format("%sw154%s", Tmdb.BASE_IMAGE_URL, this.poster_path);
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getBackdrop() {
        return String.format("%sw780%s", Tmdb.BASE_IMAGE_URL, this.backdrop_path);
    }
}
