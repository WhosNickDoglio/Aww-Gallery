package com.nicholasdoglio.eyebleach;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.nicholasdoglio.eyebleach.model.reddit.Child;

import java.util.ArrayList;
import java.util.List;


public class PhotoDetailPagerActivity extends AppCompatActivity {
    private List<Child> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        int photoPosition = getIntent().getIntExtra("ImagePosition", 0);

        ViewPager viewPager = (ViewPager) findViewById(R.id.gallery_view_pager);
        viewPager.setAdapter(new PhotoDetailPagerAdapter(this, posts));

        viewPager.setCurrentItem(photoPosition);
    }
}
