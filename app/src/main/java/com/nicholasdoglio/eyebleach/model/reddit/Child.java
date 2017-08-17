package com.nicholasdoglio.eyebleach.model.reddit;


import com.squareup.moshi.Json;

public class Child {
    @Json(name = "kind")
    private String kind;
    @Json(name = "data")
    private ChildData data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ChildData getChildData() {
        return data;
    }

    public void setChildData(ChildData data) {
        this.data = data;
    }

}
