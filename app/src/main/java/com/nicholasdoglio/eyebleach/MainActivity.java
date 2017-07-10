package com.nicholasdoglio.eyebleach;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import com.nicholasdoglio.eyebleach.model.Child;
import com.nicholasdoglio.eyebleach.model.RedditPost;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String REDDIT_BASE_URL = "https://www.reddit.com/r/";
    private List<Child> posts = new ArrayList<>();
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(REDDIT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RedditJSON redditJSON = retrofit.create(RedditJSON.class);

        Call<RedditPost> call = redditJSON.getPosts("rarepuppers", 50);

        call.enqueue(new Callback<RedditPost>() {
            @Override
            public void onResponse(Call<RedditPost> call, Response<RedditPost> response) {
                for (int i = 0; i < response.body().getData().getChildren().size(); i++) {

                    if (response.body().getData().getChildren().get(i).getData().getSelftextHtml() != null) {
                        i++;
                    } else {
                        posts.add(response.body().getData().getChildren().get(i));
                    }
                }

                ImageAdapter imageAdapter = new ImageAdapter(MainActivity.this, posts);

                gridView = (GridView) findViewById(R.id.picture_grid_view);
                gridView.setAdapter(imageAdapter);
            }

            @Override
            public void onFailure(Call<RedditPost> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to pull from Reddit right now.", t);

            }
        });
    }
}
