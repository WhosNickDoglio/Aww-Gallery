package com.nicholasdoglio.eyebleach;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private String version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        TextView textViewVersion = (TextView) findViewById(R.id.version_text_view);
        textViewVersion.setText("Version: " + version);

        TextView textViewDeveloper = (TextView) findViewById(R.id.developer_text_view);
        textViewDeveloper.setOnClickListener(view -> openWebPage("https://whosnickdoglio.github.io/"));

        TextView textViewDesigner = (TextView) findViewById(R.id.designer_text_view);
        textViewDesigner.setOnClickListener(view -> openWebPage("http://guzzgonzalez.com/"));

        TextView textViewSource = (TextView) findViewById(R.id.source_code_text_view);
        textViewSource.setOnClickListener(view -> openWebPage("https://github.com/WhosNickDoglio/Aww-Gallery"));


    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
