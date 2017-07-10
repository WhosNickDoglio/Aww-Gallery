package com.nicholasdoglio.eyebleach;



import com.nicholasdoglio.eyebleach.model.RedditPost;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RedditJSON {

    @GET("{subreddit}/.json")
    Call<RedditPost> getPosts(@Path("subreddit") String subreddit,
                              @Query("limit") int limit);
}
