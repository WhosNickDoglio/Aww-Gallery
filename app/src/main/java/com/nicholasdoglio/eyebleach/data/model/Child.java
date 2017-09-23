package com.nicholasdoglio.eyebleach.data.model;

import com.squareup.moshi.Json;

public class Child {
    @Json(name = "kind")
    private String kind;
    @Json(name = "data")
    private ChildData childData;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ChildData getChildData() {
        return childData;
    }

    public void setChildData(ChildData childData) {
        this.childData = childData;
    }
}
