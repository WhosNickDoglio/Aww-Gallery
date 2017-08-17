package com.nicholasdoglio.eyebleach;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.nicholasdoglio.eyebleach.model.reddit.Child;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhotoDetailPagerActivity extends AppCompatActivity {
    @BindView(R.id.gallery_view_pager)
    ViewPager viewPager;
    private List<Child> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        viewPager.setAdapter(new PhotoDetailPagerAdapter(this, posts));
    }
}
