package com.supernova.jetpack.movietron.model;

@SuppressWarnings("unused")
public class Item {

    public static final String TYPE_MOVIE = "movie";
    public static final String TYPE_TV = "tv";

    private int id;
    private String title;
    private String overview;
    private String poster;
    private String backdrop;
    private String release;

    public Item(int id, String title, String overview, String release, String poster, String backdrop) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.release = release;
        this.poster = poster;
        this.backdrop = backdrop;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
