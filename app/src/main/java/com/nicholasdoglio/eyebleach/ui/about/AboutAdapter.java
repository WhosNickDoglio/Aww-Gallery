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
package com.nicholasdoglio.eyebleach.ui.about;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.data.model.about.AboutInfo;
import com.nicholasdoglio.eyebleach.util.Intents;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nicholas Doglio
 */
public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {
    private Context aboutContext;

    private List<AboutInfo> aboutList = new ArrayList<>();

    AboutAdapter(Context aboutContext) {
        this.aboutContext = aboutContext;
        populateList(aboutContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_about_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.aboutContentImageView.setImageResource(aboutList.get(position).getImageID());
        holder.aboutContentDescriptionImageView.setText(aboutList.get(position).getContentName());
    }

    @Override
    public int getItemCount() {
        return aboutList.size();
    }

    private void populateList(Context context) {
        aboutList.add(new AboutInfo(R.drawable.ic_info, Intents.INSTANCE.provideVersion(context), "https://github.com/WhosNickDoglio/Aww-Gallery/releases"));
        aboutList.add(new AboutInfo(R.drawable.ic_feedback, "Feedback", ""));
        aboutList.add(new AboutInfo(R.drawable.ic_info, "Libraries", "https://htmlpreview.github.io/?https://github.com/WhosNickDoglio/Aww-Gallery/blob/master/libaries.html"));
        aboutList.add(new AboutInfo(R.drawable.ic_github, "Source code", "https://github.com/WhosNickDoglio/Aww-Gallery"));
        aboutList.add(new AboutInfo(R.drawable.dev_photo, "Developed by Nicholas Doglio", "https://whosnickdoglio.github.io/"));
        aboutList.add(new AboutInfo(R.drawable.designer_photo, "Icon & Graphics by Guzman Gonzalez", "http://guzzgonzalez.com/"));
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.content_photo)
        ImageView aboutContentImageView;
        @BindView(R.id.content_description)
        TextView aboutContentDescriptionImageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (aboutList.get(getAdapterPosition()).getContentName().equalsIgnoreCase("Feedback")) {
                Intents.INSTANCE.composeEmail(aboutContext);
            } else {
                Intents.INSTANCE.openWebPage(aboutContext, aboutList.get(getAdapterPosition()).getContentLink());

            }
        }
    }
}