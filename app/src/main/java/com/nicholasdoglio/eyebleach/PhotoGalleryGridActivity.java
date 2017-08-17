package com.nicholasdoglio.eyebleach;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.nicholasdoglio.eyebleach.model.reddit.Child;
import com.nicholasdoglio.eyebleach.model.reddit.Multireddit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class PhotoGalleryGridActivity extends AppCompatActivity {

    private static final String TAG = PhotoGalleryGridActivity.class.getSimpleName();
    private static final String REDDIT_MULTI_BASE = "https://www.reddit.com/user/NicholasDoglio/m/cuteanimals/";
    private static final int IMAGES_LOADED = 50;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.grid_progress)
    ProgressBar progressBar;
    @BindView(R.id.grid_recycler)
    RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private List<Child> posts = new ArrayList<>();
    private List<Child> loadMore = new ArrayList<>();
    private PhotoGalleryGridAdapter photoGalleryGridAdapter;
    private CompositeDisposable compositeDisposable;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 10;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_grid);
        ButterKnife.bind(this);

        compositeDisposable = new CompositeDisposable();

        initViews();
        fetchData();
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
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
        }

        return true;
    }

    private void initViews() {
        photoGalleryGridAdapter = new PhotoGalleryGridAdapter(PhotoGalleryGridActivity.this, posts);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        } else {
            layoutManager = new GridLayoutManager(getApplicationContext(), 6);
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(photoGalleryGridAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    loadMorePosts();
                    loading = true;
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            fetchData();
            resetSwipe();
        });

        progressBar.setVisibility(View.VISIBLE);
    }

    private void resetSwipe() {
        previousTotal = 0;
        firstVisibleItem = 0;
        visibleItemCount = 0;
        totalItemCount = 0;
    }

    private void fetchData() {
        RedditJSON redditJSON = buildRetrofit();

        compositeDisposable.add(redditJSON.getMultiPosts(IMAGES_LOADED, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleInitialResponse, this::handleError));
    }

    private void loadMorePosts() {
        String after = "t3_" + posts.get(posts.size() - 1).getChildData().getId();
        RedditJSON redditJSON = buildRetrofit();

        compositeDisposable.add(redditJSON.getMultiPosts(IMAGES_LOADED, after)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLoadMoreResponses, this::handleError));

        photoGalleryGridAdapter.notifyDataSetChanged();
    }

    private void handleInitialResponse(Multireddit multireddit) {
        photoGalleryGridAdapter.clear();
        progressBar.setVisibility(View.INVISIBLE);
        addImagesToList(multireddit, posts);
        swipeRefreshLayout.setRefreshing(false);
        photoGalleryGridAdapter.notifyDataSetChanged();
    }

    private void handleLoadMoreResponses(Multireddit multireddit) {
        addImagesToList(multireddit, loadMore);
        photoGalleryGridAdapter.addAll(loadMore);
        loadMore.clear();
    }

    private void addImagesToList(Multireddit multireddit, List<Child> childArrayList) {
        for (int i = 0; i < multireddit.getData().getChildren().size(); i++) {
            if (multireddit.getData().getChildren().get(i).getChildData().getUrl().contains(".jpg") ||
                    multireddit.getData().getChildren().get(i).getChildData().getUrl().contains(".png")) {
                childArrayList.add(multireddit.getData().getChildren().get(i));
            }
        }
    }

    private void handleError(Throwable error) {
        Log.e(TAG, "handleError: " + error.getLocalizedMessage(), error);
    }

    private RedditJSON buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(REDDIT_MULTI_BASE)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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