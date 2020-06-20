package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.widget.Toolbar;

public class StatusActivity extends AppCompatActivity {

    WebView checkupWV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Corona Status");

        checkupWV = (WebView) findViewById(R.id.checkupWV);
        checkupWV.setWebViewClient(new WebViewClient());
        checkupWV.loadUrl("https://www.covid19india.org/");
        WebSettings webSettings = checkupWV.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
