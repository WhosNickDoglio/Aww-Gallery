package com.nicholasdoglio.eyebleach.model.reddit;

import com.squareup.moshi.Json;

public class Resolution_ {

    @Json(name = "url")
    private String url;
    @Json(name = "width")
    private Integer width;
    @Json(name = "height")
    private Integer height;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}
