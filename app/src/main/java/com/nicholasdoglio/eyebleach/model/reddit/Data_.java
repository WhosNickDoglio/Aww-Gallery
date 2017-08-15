package com.nicholasdoglio.eyebleach.model.reddit;

import com.squareup.moshi.Json;


public class Data_ {


    @Json(name = "selftext")
    private String selftext;
    @Json(name = "preview")
    private Preview preview;
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

    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
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
