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
package com.nicholasdoglio.eyebleach.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;

import com.nicholasdoglio.eyebleach.ui.about.AboutActivity;
import com.nicholasdoglio.eyebleach.ui.photodetail.PhotoDetailActivity;

/**
 * @author Nicholas Doglio
 */
public class Intents {

    public static void openWebPage(Context context, String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void shareUrl(Context context, String url) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent, "Share your cute animals via: "));
    }

    public static void composeEmail(Context context) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: NicholasDoglio@Gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Aww Gallery Feedback");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void startDetailActivity(View view, String id) {
        Intent photoDetailIntent = new Intent(view.getContext(), PhotoDetailActivity.class);
        photoDetailIntent.putExtra("ID", id);
        view.getContext().startActivity(photoDetailIntent);
    }

    public static void startAboutActivity(Context context) {
        Intent aboutIntent = new Intent(context, AboutActivity.class);
        context.startActivity(aboutIntent);
    }

    public static String provideVersion(Context context) {
        String version = "";

        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "Version: " + version;
    }
}