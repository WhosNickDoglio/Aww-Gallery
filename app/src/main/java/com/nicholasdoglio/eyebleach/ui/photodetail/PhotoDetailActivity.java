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
package com.nicholasdoglio.eyebleach.ui.photodetail;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.util.Intents;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * @author Nicholas Doglio
 */
public class PhotoDetailActivity extends AppCompatActivity implements PhotoDetailContract.View {
    @BindView(R.id.gallery_recyclerview)
    RecyclerView photoDetailRecyclerView;

    @Inject
    PhotoDetailPresenter photoDetailPresenter;

    private PhotoDetailAdapter photoDetailAdapter;
    private LinearLayoutManager layoutManager;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 1;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
        photoDetailPresenter.load();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(photoDetailRecyclerView);
        photoDetailAdapter = new PhotoDetailAdapter(this);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        photoDetailRecyclerView.setAdapter(photoDetailAdapter);
        photoDetailRecyclerView.setLayoutManager(layoutManager);
        photoDetailRecyclerView.setHasFixedSize(true);

        //I don't like this but it seems like the only thing that consistently opens up to the right photo
        new Handler().postDelayed(() -> photoDetailRecyclerView.getLayoutManager().scrollToPosition(getIntent().getExtras().getInt("POSITION")), 200);

        photoDetailPresenter.photoDetailPagedList.observe(PhotoDetailActivity.this, childData -> photoDetailAdapter.setList(childData));
        photoDetailRecyclerView.getItemAnimator().setChangeDuration(0);


        photoDetailRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    loadMore();
                    loading = true;
                }
            }
        });
    }

    public void loadMore() {
        photoDetailPresenter.loadMore();
        photoDetailAdapter.notifyItemRangeChanged(0, photoDetailAdapter.getItemCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String postUrl = photoDetailAdapter.getCurrentList().get(layoutManager.findFirstVisibleItemPosition()).fullUrl();

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.share_item:
                Intents.shareUrl(this, postUrl);
                return true;
            case R.id.open_source:
                Intents.openWebPage(this, postUrl);
                return true;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        photoDetailPresenter.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        photoDetailPresenter.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        photoDetailPresenter.takeView(this);
    }
}