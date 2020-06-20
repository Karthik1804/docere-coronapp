package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HelplineActivity extends AppCompatActivity {

    Button callNow;
    Button download;
    Button tnEPass;
    Button indEPass;
    ImageView arogyaSetuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HelpLine");

        callNow = (Button) findViewById(R.id.callNow);
        download = (Button) findViewById(R.id.arogyaSetuDownload);
        tnEPass = (Button) findViewById(R.id.applyTNPass);
        indEPass = (Button) findViewById(R.id.applyINDPass);
        arogyaSetuIcon = (ImageView) findViewById(R.id.imageView);
        arogyaSetuIcon.setImageResource(R.drawable.arogya_setu);

        callNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:044-2951050024"));
                startActivity(intent);
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=nic.goi.aarogyasetu"));
                startActivity(intent);
            }
        });

        tnEPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tnepass.tnega.org/"));
                startActivity(intent);
            }
        });

        indEPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://serviceonline.gov.in/epass/"));
                startActivity(intent);
            }

        });
    }
}
