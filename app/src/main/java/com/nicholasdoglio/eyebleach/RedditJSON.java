package com.nicholasdoglio.eyebleach;

import com.nicholasdoglio.eyebleach.model.reddit.Multireddit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditJSON {

    @GET(".json")
    Observable<Multireddit> getPosts(@Query("limit") int limit, @Query("after") String after);
}
