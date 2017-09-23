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
import com.nicholasdoglio.eyebleach.data.model.ChildData;
import com.nicholasdoglio.eyebleach.data.model.Multireddit;
import com.nicholasdoglio.eyebleach.util.Intents;

import java.util.ArrayList;
import java.util.List;

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
    PhotoGridAdapter photoGridAdapter;

    @Inject
    PhotoGridPresenter photoGridPresenter;

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
        progressBar.setVisibility(View.VISIBLE);

        GridLayoutManager layoutManager;//TODO: This will be 6 and 9 for tablets
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        } else {
            layoutManager = new GridLayoutManager(getApplicationContext(), 6);
        }

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            fetchData();
            photoGridAdapter.notifyDataSetChanged();
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void fetchData() {
        photoGridPresenter.load();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void updateListMulti(Multireddit multireddit) {
        List<ChildData> childData = new ArrayList<>();
        photoGridPresenter.filterForImages(multireddit, childData);
        photoGridAdapter = new PhotoGridAdapter(PhotoGridActivity.this, childData);
        recyclerView.setAdapter(photoGridAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateListChildData(List<ChildData> childData) {
        photoGridAdapter = new PhotoGridAdapter(PhotoGridActivity.this, childData);
        recyclerView.setAdapter(photoGridAdapter);
        progressBar.setVisibility(View.INVISIBLE);
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