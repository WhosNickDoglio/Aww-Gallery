package com.nicholasdoglio.eyebleach;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.version_text_view)
    TextView textViewVersion;
    @BindView(R.id.developer_text_view)
    TextView textViewDeveloper;
    @BindView(R.id.designer_text_view)
    TextView textViewDesigner;
    @BindView(R.id.source_code_text_view)
    TextView textViewSource;
    @BindView(R.id.feedback_text_view)
    TextView textViewFeeback;
    private String version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        textViewVersion.setText(provideVersion());
        textViewDeveloper.setOnClickListener(view -> openWebPage("https://whosnickdoglio.github.io/"));
        textViewDesigner.setOnClickListener(view -> openWebPage("http://guzzgonzalez.com/"));
        textViewSource.setOnClickListener(view -> openWebPage("https://github.com/WhosNickDoglio/Aww-Gallery"));
        textViewFeeback.setOnClickListener(view -> composeEmail());
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public String provideVersion() {
        try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "Version: " + version;
    }

    public void composeEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "NicholasDoglio@Gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Aww Gallery Feedback");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}