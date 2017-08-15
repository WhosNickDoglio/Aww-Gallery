package com.nicholasdoglio.eyebleach.model.reddit;

import com.squareup.moshi.Json;

public class Variants {

    @Json(name = "gif")
    private Gif gif;
    @Json(name = "mp4")
    private Mp4 mp4;

    public Gif getGif() {
        return gif;
    }

    public void setGif(Gif gif) {
        this.gif = gif;
    }

    public Mp4 getMp4() {
        return mp4;
    }

    public void setMp4(Mp4 mp4) {
        this.mp4 = mp4;
    }

}
