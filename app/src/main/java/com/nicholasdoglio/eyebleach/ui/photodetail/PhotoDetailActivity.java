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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.util.Intents;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

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
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        photoDetailPresenter.load();
//        load();

        position = getIntent().getExtras().getInt("POSITION");

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(photoDetailRecyclerView);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        photoDetailRecyclerView.setLayoutManager(layoutManager);
        photoDetailRecyclerView.setHasFixedSize(true);
        photoDetailRecyclerView.getItemAnimator().setChangeDuration(0);

        photoDetailAdapter = new PhotoDetailAdapter(this, photoDetailRecyclerView);
        photoDetailAdapter.setOnLoadMoreListener(this::loadMore);
        photoDetailPresenter.getPhotoDetailList().observe(this, childData -> photoDetailAdapter.setList(childData));
        photoDetailRecyclerView.setAdapter(photoDetailAdapter);


        //I don't like this but it seems like the only thing that consistently opens up to the right photo
        Completable.timer(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> photoDetailRecyclerView.scrollToPosition(position));
    }

    public void loadMore() {
        photoDetailPresenter.loadMore();
    }

    @Override
    public void load(List<ChildData> childData) {
        photoDetailPresenter.load();
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
                Intents.INSTANCE.shareUrl(this, postUrl);
                return true;
            case R.id.open_source:
                Intents.INSTANCE.openWebPage(this, postUrl);
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