package com.supernova.jetpack.movietron.data.source.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@SuppressWarnings("unused")
@Entity(tableName = "items")
public class ItemEntity {
    @Ignore
    public static final String TYPE_MOVIE = "movie";

    @Ignore
    public static final String TYPE_TV = "tv";

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "release")
    private String release;

    @ColumnInfo(name = "poster")
    private String poster;

    @ColumnInfo(name = "backdrop")
    private String backdrop;

    @ColumnInfo(name = "favorited")
    private boolean favorited = false;

    @Ignore
    public ItemEntity() {
    }

    public ItemEntity(int id, String type, String title, String overview, String release, String poster, String backdrop) {
        this.id = id;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
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

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }
}
