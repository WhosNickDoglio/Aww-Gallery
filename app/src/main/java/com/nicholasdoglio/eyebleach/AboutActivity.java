package com.nicholasdoglio.eyebleach;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {
    private String version;
    private View about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        about = new AboutPage(this)
                .isRTL(false)
                .setDescription(" ")
                .addItem(new Element().setTitle("Version: " + version))
                .addWebsite("https://WhosNickDoglio.github.io", "Developed by Nicholas Doglio")
                .addWebsite("Guzzgonzalez.com", "Icon and graphics designed by Guzmán González")
                .create();

        setContentView(about);
    }
}
