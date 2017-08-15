package com.nicholasdoglio.eyebleach.model.reddit;


import com.squareup.moshi.Json;

public class Child {
    @Json(name = "kind")
    private String kind;
    @Json(name = "data")
    private Data_ data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Data_ getData() {
        return data;
    }

    public void setData(Data_ data) {
        this.data = data;
    }

}
