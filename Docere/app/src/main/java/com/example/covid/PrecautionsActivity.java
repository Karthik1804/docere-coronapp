package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class PrecautionsActivity extends AppCompatActivity {

    Switch langtoggle;
    ImageView precationImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precautions);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Precautions");

        langtoggle = (Switch) findViewById(R.id.switch2);
        precationImage = (ImageView) findViewById(R.id.precautionsImage);
        precationImage.setImageResource(R.drawable.english);

        langtoggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    precationImage.setImageResource(R.drawable.tamil);
                    Toast.makeText(getBaseContext(), "You selected Tamil...", Toast.LENGTH_SHORT).show();
                } else {
                    precationImage.setImageResource(R.drawable.english);
                    Toast.makeText(getBaseContext(), "You Selected English...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
