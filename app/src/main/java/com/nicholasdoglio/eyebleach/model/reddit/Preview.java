package com.nicholasdoglio.eyebleach.model.reddit;

import com.squareup.moshi.Json;

import java.util.List;

public class Preview {

    private transient String id;
    @Json(name = "images")
    private List<Image> images = null;
    @Json(name = "enabled")
    private Boolean enabled;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
