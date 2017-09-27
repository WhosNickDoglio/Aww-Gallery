package com.nicholasdoglio.eyebleach.ui.photodetail;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.ChildData;
import com.nicholasdoglio.eyebleach.util.Intents;

import java.util.ArrayList;
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
    private List<ChildData> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        removeActionBar();
        photoDetailPresenter.load();
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

    @Override
    public void updateList(List<ChildData> childDataList) {
        String id = getIntent().getStringExtra("ID");//This works
        int index = 0;
        viewPager.setAdapter(new PhotoDetailAdapter(this, childDataList));

        for (int i = 0; i < childDataList.size(); i++) {
            if (childDataList.get(i).getId().equals(id)) {
                index = childDataList.indexOf(childDataList.get(i));//Sort of works
            }
        }

        //Swiping is really broken crazy weird order, seemingly random
        posts.addAll(childDataList);

        viewPager.setAdapter(new PhotoDetailAdapter(this, posts));
        viewPager.setCurrentItem(index);
    }
}
