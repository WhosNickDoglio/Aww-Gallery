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
package com.nicholasdoglio.eyebleach.ui.photogrid;

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
import com.nicholasdoglio.eyebleach.util.Intents;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * @author Nicholas Doglio
 */
public class PhotoGridActivity extends AppCompatActivity implements PhotoGridContract.View {
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout photoGridSwipeRefreshLayout;
    @BindView(R.id.grid_progress)
    ProgressBar photoGridProgressBar;
    @BindView(R.id.grid_recycler)
    RecyclerView photoGridRecyclerView;
    @Inject
    PhotoGridPresenter photoGridPresenter;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 3;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_grid);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
        fetchData();

        PhotoGridAdapter photoGridAdapter = new PhotoGridAdapter(this);

        GridLayoutManager layoutManager;//TODO: This will be 6 and 9 for tablets
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        } else {
            layoutManager = new GridLayoutManager(getApplicationContext(), 6);
        }

        photoGridSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        photoGridSwipeRefreshLayout.setOnRefreshListener(() -> {
            photoGridPresenter.load();
            photoGridSwipeRefreshLayout.setRefreshing(false);
            previousTotal = 0;
            firstVisibleItem = 0;
            visibleItemCount = 0;
            totalItemCount = 0;

        });

        photoGridRecyclerView.setHasFixedSize(true);
        photoGridRecyclerView.setLayoutManager(layoutManager);
        photoGridPresenter.childData.observe(PhotoGridActivity.this, photoGridAdapter::setList);
        photoGridRecyclerView.setAdapter(photoGridAdapter);

        photoGridProgressBar.setVisibility(View.INVISIBLE);

        photoGridRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
    public void fetchData() {// TODO: Sometimes the scroll position gets messed up
        photoGridProgressBar.setVisibility(View.VISIBLE); //Stopped working with addition of Paging library
        photoGridPresenter.load();
        photoGridSwipeRefreshLayout.setRefreshing(false);
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