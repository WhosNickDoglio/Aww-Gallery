package com.nicholasdoglio.eyebleach.model.reddit;

import com.squareup.moshi.Json;

import java.util.List;


public class Gif {

    private transient String id;
    @Json(name = "source")
    private Source_ source;
    @Json(name = "resolutions")
    private List<Resolution_> resolutions = null;

    public Source_ getSource() {
        return source;
    }

    public void setSource(Source_ source) {
        this.source = source;
    }

    public List<Resolution_> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<Resolution_> resolutions) {
        this.resolutions = resolutions;
    }

}
