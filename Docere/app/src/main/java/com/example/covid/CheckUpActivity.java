package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CheckUpActivity extends AppCompatActivity {

    WebView checkupWV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Check Online Now!");

        checkupWV = (WebView) findViewById(R.id.checkupWV);
        checkupWV.setWebViewClient(new WebViewClient());
        checkupWV.loadUrl("https://covid.apollo247.com/?utm_source=linkedin&utm_medium=organic&utm_campaign=bot_scanner");
        WebSettings webSettings = checkupWV.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
