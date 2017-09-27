package com.nicholasdoglio.eyebleach.ui.photogrid;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.util.Intents;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class PhotoGridActivity extends DaggerAppCompatActivity implements PhotoGridContract.View {
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.grid_progress)
    ProgressBar progressBar;
    @BindView(R.id.grid_recycler)
    RecyclerView recyclerView;
    @Inject
    PhotoGridPresenter photoGridPresenter;

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
        initViews();
        fetchData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grid_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_item:
                Intents.startAboutActivity(this);
                return true;
        }
        return true;
    }

    @Override
    public void initViews() {
        progressBar.setVisibility(View.VISIBLE); //Stopped working with addition of Paging library

        PhotoGridAdapter photoGridAdapter = new PhotoGridAdapter(this);

        GridLayoutManager layoutManager;//TODO: This will be 6 and 9 for tablets
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        } else {
            layoutManager = new GridLayoutManager(getApplicationContext(), 6);
        }

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            fetchData();
            resetSwipe();
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        photoGridPresenter.childData.observe(this, photoGridAdapter::setList);
        recyclerView.setAdapter(photoGridAdapter);

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
                    photoGridPresenter.loadMore();
                    loading = true;
                }
            }
        });

        progressBar.setVisibility(View.INVISIBLE);
    }

    private void resetSwipe() {
        previousTotal = 0;
        firstVisibleItem = 0;
        visibleItemCount = 0;
        totalItemCount = 0;
    }

    @Override
    public void fetchData() {
        photoGridPresenter.load();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        photoGridPresenter.takeView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        photoGridPresenter.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        photoGridPresenter.clear();
    }
}