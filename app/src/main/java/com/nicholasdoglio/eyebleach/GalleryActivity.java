package com.nicholasdoglio.eyebleach;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.nicholasdoglio.eyebleach.model.Child;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ArrayList<Child> posts = getIntent().getParcelableArrayListExtra("POSTS");

        ViewPager viewPager = (ViewPager) findViewById(R.id.gallery_view_pager);
        viewPager.setAdapter(new GalleryPagerAdapter(this, posts));

    }
}
