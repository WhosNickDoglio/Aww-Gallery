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
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
public class PhotoDetailActivity extends AppCompatActivity implements PhotoDetailContract.View {
    @BindView(R.id.gallery_view_pager)
    ViewPager photoDetailViewPager;
    @Inject
    PhotoDetailPresenter photoDetailPresenter;

    private List<ChildData> posts = new ArrayList<>();
    private int index = 0;
    private PhotoDetailAdapter photoDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        photoDetailPresenter.load();

        photoDetailViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == photoDetailAdapter.loadMoreCallPosition()) {
                    photoDetailPresenter.loadMore();
                    Toast.makeText(PhotoDetailActivity.this, "THIS IS WHERE I WOULD LOAD", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String postUrl = "https://reddit.com" + posts.get(photoDetailViewPager.getCurrentItem()).getPermalink();//Move this to ChildData as fullUrl

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
        posts.addAll(childDataList);
        photoDetailAdapter = new PhotoDetailAdapter(this, posts);
        photoDetailViewPager.setAdapter(photoDetailAdapter);
        setPosition();

    }

    public void addMorePosts(List<ChildData> childData) {
        posts.addAll(childData);
        photoDetailAdapter.notifyDataSetChanged();
    }

    public void setPosition() {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId().equals(getIntent().getStringExtra("ID"))) {
                index = posts.indexOf(posts.get(i));
            }
        }
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