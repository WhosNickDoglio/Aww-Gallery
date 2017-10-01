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
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.reddit.ChildData;
import com.nicholasdoglio.eyebleach.util.Intents;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author Nicholas Doglio
 */
public class PhotoDetailActivity extends DaggerAppCompatActivity implements PhotoDetailContract.View {
    @BindView(R.id.gallery_view_pager)
    ViewPager photoDetailViewPager;
    @Inject
    PhotoDetailPresenter photoDetailPresenter;

    private List<ChildData> posts;
    private int index = 0;
    private PhotoDetailAdapter photoDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        posts = new ArrayList<>();
        photoDetailAdapter = new PhotoDetailAdapter(this, posts);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


        photoDetailPresenter.load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String postUrl = "https://reddit.com" + posts.get(photoDetailViewPager.getCurrentItem()).getPermalink();

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
    public void updateList(List<ChildData> childDataList) {
        for (int i = 0; i < childDataList.size(); i++) {
            if (childDataList.get(i).getId().equals(getIntent().getStringExtra("ID"))) {
                index = childDataList.indexOf(childDataList.get(i));
            }
        }
        posts.addAll(childDataList);
        photoDetailViewPager.setAdapter(photoDetailAdapter);
        photoDetailViewPager.setCurrentItem(index);
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