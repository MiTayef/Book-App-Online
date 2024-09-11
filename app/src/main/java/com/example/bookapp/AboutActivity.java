package com.example.bookapp;

import static android.app.PendingIntent.getActivity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AboutActivity extends AppCompatActivity {

    Dialog dialog;
    Context context;
    TextView phoneTextView, emailTextView;
    private LinearLayout adContainerViewAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        phoneTextView = findViewById(R.id.phoneTextView);
        emailTextView = findViewById(R.id.emailTextView);
        adContainerViewAbout = findViewById(R.id.adContainerViewAbout);

        //======================================
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //======================================



        // Create a new ad view.
        AdView adView = new AdView(AboutActivity.this);
        adView.setAdUnitId("ca-app-pub-7795414021148606/3713328734");
        adView.setAdSize(AdSize.BANNER);

        // Replace ad container with new ad view.
        adContainerViewAbout.removeAllViews();
        adContainerViewAbout.addView(adView);

        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



        phoneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:+8801723666913"));
                startActivity(callIntent);
            }
        });

        phoneTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) AboutActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("phone number", phoneTextView.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(AboutActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                return true;
            }
        });





        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:info.waneex@gmail.com"));
                startActivity(emailIntent);
            }
        });

        emailTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) AboutActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("email address", emailTextView.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(AboutActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                return true;
            }
        });





    }// OnCreate Method End Here



    // For Back Button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }





}//Public Class End Here