package com.nicholasdoglio.eyebleach.model.reddit;

import com.squareup.moshi.Json;


public class ChildData {

    @Json(name = "selftext")
    private String selftext;
    @Json(name = "thumbnail")
    private String thumbnail;
    @Json(name = "permalink")
    private String permalink;
    @Json(name = "url")
    private String url;
    @Json(name = "id")
    private String id;

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
