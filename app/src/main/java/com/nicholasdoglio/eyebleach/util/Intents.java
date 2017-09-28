package com.nicholasdoglio.eyebleach.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.nicholasdoglio.eyebleach.ui.about.AboutDialogActivity;
import com.nicholasdoglio.eyebleach.ui.photodetail.PhotoDetailActivity;

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
        Intent aboutIntent = new Intent(context, AboutDialogActivity.class);
        context.startActivity(aboutIntent);
    }
}