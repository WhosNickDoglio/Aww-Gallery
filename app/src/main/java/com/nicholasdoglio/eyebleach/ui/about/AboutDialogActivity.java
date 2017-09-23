package com.nicholasdoglio.eyebleach.ui.about;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nicholasdoglio.eyebleach.R;
import com.nicholasdoglio.eyebleach.util.Intents;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutDialogActivity extends AppCompatActivity {
    //TODO: Move this into a dialog from menu instead of own Activity (Similar to Action Launcher)

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
        textViewDeveloper.setOnClickListener(view -> Intents.openWebPage(this, "https://whosnickdoglio.github.io/"));
        textViewDesigner.setOnClickListener(view -> Intents.openWebPage(this, "http://guzzgonzalez.com/"));
        textViewSource.setOnClickListener(view -> Intents.openWebPage(this, "https://github.com/WhosNickDoglio/Aww-Gallery"));
        textViewFeeback.setOnClickListener(view -> Intents.composeEmail(this));
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
}