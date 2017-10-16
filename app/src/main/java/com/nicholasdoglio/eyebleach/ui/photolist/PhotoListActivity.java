/*
    Aww Gallery
    Copyright (C) 2017  Nicholas Doglio

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.nicholasdoglio.eyebleach.ui.photolist;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.util.Intents;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * @author Nicholas Doglio
 */
public class PhotoListActivity extends AppCompatActivity implements PhotoListContract.View {
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout photoGridSwipeRefreshLayout;
    @BindView(R.id.list_progress)
    ProgressBar photoGridProgressBar;
    @BindView(R.id.list_recycler)
    RecyclerView photoGridRecyclerView;
    @Inject
    PhotoListPresenter photoListPresenter;

    private PhotoGridAdapter photoGridAdapter;
    private List<ChildData> photoGridList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        photoGridList = new ArrayList<>();

        fetchData();

        GridLayoutManager layoutManager;//TODO: This will be 6 and 9 for tablets
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        } else {
            layoutManager = new GridLayoutManager(getApplicationContext(), 6);
        }

        photoGridRecyclerView.setLayoutManager(layoutManager);
        photoGridRecyclerView.setHasFixedSize(true);
        photoGridRecyclerView.getItemAnimator().setChangeDuration(0);

        photoGridAdapter = new PhotoGridAdapter(this, photoGridList, photoGridRecyclerView);
        photoListPresenter.getPhotoList().observe(this, childData -> photoGridAdapter.addMore(childData));
        photoGridRecyclerView.setAdapter(photoGridAdapter);
        photoGridAdapter.setOnLoadMoreListener(this::loadMore);

        photoGridSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        photoGridSwipeRefreshLayout.setOnRefreshListener(this::refresh);
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
                Intents.INSTANCE.startAboutActivity(this);
                return true;
        }
        return true;
    }

    public void loadMore() {
        photoListPresenter.loadMore();
    }


    public void refresh() {//Sometimes position moves, want it to stay at the top
        photoListPresenter.load();
        photoGridAdapter.swipeRefresh();
        photoGridRecyclerView.scrollToPosition(0);
    }

    @Override
    public void load(List<ChildData> childDataList) {
        photoGridList.addAll(childDataList);
        photoGridSwipeRefreshLayout.setRefreshing(false);
        photoGridProgressBar.setVisibility(View.INVISIBLE); //I only want the progress bar when photoGridRecyclerView is empty, how?
    }

    @Override
    public void fetchData() {// TODO: Sometimes the scroll position gets messed up
        photoListPresenter.load();
    }

    @Override
    protected void onResume() {
        super.onResume();
        photoListPresenter.takeView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        photoListPresenter.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        photoListPresenter.clear();
    }
}