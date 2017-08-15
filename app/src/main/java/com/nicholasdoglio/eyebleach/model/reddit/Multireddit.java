package com.nicholasdoglio.eyebleach.model.reddit;


import com.squareup.moshi.Json;

public class Multireddit {

    @Json(name = "kind")
    private String kind;
    @Json(name = "data")
    private Data data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
