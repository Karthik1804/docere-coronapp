package com.example.covid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 1234 ;
    List<AuthUI.IdpConfig> providers;
    TextView profileDesc;
    Button checkUp;
    Button covidStatus;
    Button precautions;
    Button helpline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        profileDesc = (TextView) findViewById(R.id.profileDetails);
        checkUp = (Button) findViewById(R.id.checkUp);
        covidStatus = (Button) findViewById(R.id.status);
        precautions = (Button) findViewById(R.id.precautions);
        helpline = (Button) findViewById(R.id.helpline);

        checkUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent checkUpIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://covid.apollo247.com/?utm_source=linkedin&utm_medium=organic&utm_campaign=bot_scanner"));
                Intent checkUpIntent = new Intent(MainActivity.this, CheckUpActivity.class);
                startActivity(checkUpIntent);
            }
        });
        covidStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent statusIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.covid19india.org/"));
                Intent statusIntent = new Intent(MainActivity.this, StatusActivity.class);
                startActivity(statusIntent);
            }
        });
        precautions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent precautionsIntent = new Intent(MainActivity.this, PrecautionsActivity.class);
                startActivity(precautionsIntent);
            }
        });


        helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helplineIntent = new Intent(MainActivity.this, HelplineActivity.class);
                startActivity(helplineIntent);
            }
        });

        providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build()
        );
        showSignInOptions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem item = menu.findItem(R.id.signOutBtn);
        Drawable icon = getResources().getDrawable(R.drawable.ic_signout);
        icon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        item.setIcon(icon);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.signOutBtn) {
            AuthUI.getInstance()
                    .signOut(MainActivity.this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            showSignInOptions();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.Mytheme)
                        .build(),MY_REQUEST_CODE
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MY_REQUEST_CODE)
        {
            IdpResponse response= IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK)
            {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                getSupportActionBar().setTitle("Hi "+user.getDisplayName());
//                firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getDisplayName());
            if (user.getEmail() == null) {
                    profileDesc.setText("\n"+"   Email ID: -\n"+"   Mobile Number: "+user.getPhoneNumber()+"\n");
                    Toast.makeText(this, "Welcome User!", Toast.LENGTH_SHORT).show();
            } else{
                    profileDesc.setText("\n   Email ID: "+user.getEmail()+"\n   Mobile Number: -\n");
                    Toast.makeText(this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
            }
            }
            else
            {
                Toast.makeText(this, ""+response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
