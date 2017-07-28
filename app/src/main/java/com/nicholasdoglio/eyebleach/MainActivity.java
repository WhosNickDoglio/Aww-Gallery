package com.nicholasdoglio.eyebleach;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.nicholasdoglio.eyebleach.model.Child;
import com.nicholasdoglio.eyebleach.model.Multireddit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String REDDIT_MULTI_BASE = "https://www.reddit.com/user/NicholasDoglio/m/cuteanimals/";
    private static final int IMAGES_LOADED =  40;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_item:
                Intent aboutItent = new Intent(this, AboutActivity.class);
                startActivity(aboutItent);
                return true;
            case R.id.gallery_item:
                Intent galleryIntent = new Intent(this, GalleryActivity.class);
                galleryIntent.putParcelableArrayListExtra("POSTS", (ArrayList<Child>) posts);
                startActivity(galleryIntent);
                return true;
        }

        return false;
    }

    private void initViews() {

        recyclerView = (SuperRecyclerView) findViewById(R.id.super_recycler);

        recyclerView.setRefreshListener(() -> {
            fetchData("");
            recyclerView.setRefreshing(false);
        });

        recyclerView.setupMoreListener((overallItemsCount, itemsBeforeMore, maxLastVisiblePosition) -> loadMorePosts(), 10);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getApplicationContext(), 4);
        } else {
            layoutManager = new GridLayoutManager(getApplicationContext(), 6);
        }

        recyclerView.setLayoutManager(layoutManager);
    }

    private void loadMorePosts() {
        String after = "t3_" + posts.get(posts.size() - 1).getData().getId();
        RedditJSON redditJSON = buildRetrofit();

        compositeDisposable.add(redditJSON.getPosts(IMAGES_LOADED, after)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLoadMoreResponses, this::handleError));
    }

    private void fetchData(String after) {
        RedditJSON redditJSON = buildRetrofit();

        compositeDisposable.add(redditJSON.getPosts(IMAGES_LOADED, after)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleInitialResponse, this::handleError));
    }

    private void handleInitialResponse(Multireddit multireddit) {
        removeNonPhotoPosts(multireddit);
        imageGridAdapter = new ImageGridAdapter(MainActivity.this, posts);
        recyclerView.setAdapter(imageGridAdapter);
    }

    private void removeNonPhotoPosts(Multireddit multireddit) {
        for (int i = 0; i < multireddit.getData().getChildren().size(); i++) {
            if ((multireddit.getData().getChildren().get(i).getData().getSelftextHtml() == null) &&
                    multireddit.getData().getChildren().get(i).getData().getUrl().contains(".jpg")) {
                posts.add(multireddit.getData().getChildren().get(i));
            }
        }
    }

    private void handleLoadMoreResponses(Multireddit multireddit) {
        removeNonPhotoPosts(multireddit);
        imageGridAdapter.notifyDataSetChanged();
    }


    private void handleError(Throwable error) {
        Log.e(TAG, "handleError: " + error.getLocalizedMessage(), error);
    }

    private RedditJSON buildRetrofit() {

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(REDDIT_MULTI_BASE)
                .build().create(RedditJSON.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.dispose();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}