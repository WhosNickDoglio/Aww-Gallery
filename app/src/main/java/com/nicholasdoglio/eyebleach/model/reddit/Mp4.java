package com.nicholasdoglio.eyebleach.model.reddit;

import com.squareup.moshi.Json;

import java.util.List;

public class Mp4 {

    private transient String id;
    @Json(name = "source")
    private Source__ source;
    @Json(name = "resolutions")
    private List<Resolution__> resolutions = null;

    public Source__ getSource() {
        return source;
    }

    public void setSource(Source__ source) {
        this.source = source;
    }

    public List<Resolution__> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<Resolution__> resolutions) {
        this.resolutions = resolutions;
    }

}
