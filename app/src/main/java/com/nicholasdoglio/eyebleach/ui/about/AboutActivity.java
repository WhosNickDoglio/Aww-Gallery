/*
 *     Aww Gallery
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
package com.nicholasdoglio.eyebleach.ui.about;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nicholasdoglio.eyebleach.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nicholas Doglio
 */
public class AboutActivity extends AppCompatActivity {

    // TODO: Move this into a dialog fragment

    @BindView(R.id.about_recycler_view)
    RecyclerView aboutRecycerlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        aboutRecycerlView.setLayoutManager(new LinearLayoutManager(this));
        aboutRecycerlView.setAdapter(new AboutAdapter(this));
        aboutRecycerlView.setHasFixedSize(true);
    }
}