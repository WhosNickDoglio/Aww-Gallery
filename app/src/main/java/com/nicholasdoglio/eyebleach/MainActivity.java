package com.nicholasdoglio.eyebleach;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.nicholasdoglio.eyebleach.model.Child;
import com.nicholasdoglio.eyebleach.model.ListOfRedditPosts;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String REDDIT_MULTI_BASE = "https://www.reddit.com/user/NicholasDoglio/m/cuteanimals/";
    private SuperRecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Child> posts = new ArrayList<>();
    private ImageGridAdapter imageGridAdapter;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compositeDisposable = new CompositeDisposable();

        initViews();
        fetchData("");
    }

    private void initViews() {

        recyclerView = (SuperRecyclerView) findViewById(R.id.super_recycler);

        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData("");
                recyclerView.setRefreshing(false);
            }
        });

        recyclerView.setupMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                loadMorePosts();
            }
        }, 10);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getApplicationContext(), 4);
        } else {
            layoutManager = new GridLayoutManager(getApplicationContext(), 6);
        }

        recyclerView.setLayoutManager(layoutManager);



    }

    private void loadMorePosts() {
        int lastPost = posts.size() - 1;
        String lastPostId = posts.get(lastPost).getData().getId();
        String after = "t3_" + lastPostId;
        RedditJSON redditJSON = buildRetrofit();

        compositeDisposable.add(redditJSON.getPosts(32, after)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLoadMoreResponses, this::handleError));
    }

    private void fetchData(String after) {
        RedditJSON redditJSON = buildRetrofit();

        compositeDisposable.add(redditJSON.getPosts(32, after)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleInitialResponse, this::handleError));
    }

    private void handleInitialResponse(ListOfRedditPosts listOfRedditPosts) {
        removeNonPhotoPosts(listOfRedditPosts);
        imageGridAdapter = new ImageGridAdapter(MainActivity.this, posts);
        recyclerView.setAdapter(imageGridAdapter);

    }

    private void removeNonPhotoPosts(ListOfRedditPosts listOfRedditPosts) {
        for (int i = 0; i < listOfRedditPosts.getData().getChildren().size(); i++) {
            if ((listOfRedditPosts.getData().getChildren().get(i).getData().getSelftextHtml() == null) &&
                    listOfRedditPosts.getData().getChildren().get(i).getData().getThumbnail() != null) {
                posts.add(listOfRedditPosts.getData().getChildren().get(i));
            } else {
                continue;
            }
        }
    }

    private void handleLoadMoreResponses(ListOfRedditPosts listOfRedditPosts) {
        removeNonPhotoPosts(listOfRedditPosts);
        imageGridAdapter.notifyDataSetChanged();
    }


    private void handleError(Throwable error) {

        Toast.makeText(this, "Error " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "handleError: " + error.getLocalizedMessage(), error);
    }

    private RedditJSON buildRetrofit() {
        RedditJSON redditJSON = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(REDDIT_MULTI_BASE)
                .build().create(RedditJSON.class);

        return redditJSON;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}