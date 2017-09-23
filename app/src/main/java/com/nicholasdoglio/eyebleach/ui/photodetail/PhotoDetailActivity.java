package com.nicholasdoglio.eyebleach.ui.photodetail;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.ChildData;
import com.nicholasdoglio.eyebleach.util.Intents;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class PhotoDetailActivity extends DaggerAppCompatActivity implements PhotoDetailContract.View {
    @BindView(R.id.gallery_view_pager)
    ViewPager viewPager;
    @Inject
    PhotoDetailPresenter photoDetailPresenter;
    private List<ChildData> posts;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        removeActionBar();
        getExtras();
        initViews();
    }

    private void getExtras() {
        position = getIntent().getIntExtra("POSITION", 0);
        posts = getIntent().getParcelableArrayListExtra("POSTS");
    }

    private void initViews() {
        viewPager.setAdapter(new PhotoDetailAdapter(this, posts));
        viewPager.setCurrentItem(position);
    }

    private void removeActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String postUrl = "https://reddit.com" + posts.get(viewPager.getCurrentItem()).getPermalink();

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
