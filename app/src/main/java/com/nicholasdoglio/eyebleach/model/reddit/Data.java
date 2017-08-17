package com.nicholasdoglio.eyebleach.model.reddit;

import com.squareup.moshi.Json;

import java.util.List;


public class Data {

    @Json(name = "modhash")
    private String modhash;
    @Json(name = "children")
    private List<Child> children = null;
    @Json(name = "after")
    private String after;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
}
