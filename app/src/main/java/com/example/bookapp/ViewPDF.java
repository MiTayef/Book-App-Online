package com.example.bookapp;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewPDF extends AppCompatActivity {

    PDFView pdfView;
    RelativeLayout lottieView, noInternetBox, refreshBox;
    TextView book_title, currentPageNumber;
    RequestQueue requestQueue;
    JsonArrayRequest jsonArrayRequest;
    private LinearLayout adContainerViewPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pdf);

        pdfView = findViewById(R.id.pdfView);
        book_title = findViewById(R.id.book_title);
        currentPageNumber = findViewById(R.id.currentPageNumber);
        lottieView = findViewById(R.id.lottieView);
        noInternetBox = findViewById(R.id.noInternetBox);
        refreshBox = findViewById(R.id.refreshBox);
        adContainerViewPDF = findViewById(R.id.adContainerViewPDF);

        //======================================
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //======================================

        Bundle bundle = getIntent().getExtras();
        String pdfUrl = bundle.getString("pdfUrl");
        String title = bundle.getString("title");
        book_title.setText(title);


        // Create a new ad view.
        AdView adView = new AdView(ViewPDF.this);
        adView.setAdUnitId("ca-app-pub-7795414021148606/3713328734");
        adView.setAdSize(AdSize.BANNER);
        // Replace ad container with new ad view.
        adContainerViewPDF.removeAllViews();
        adContainerViewPDF.addView(adView);
        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        // For load and View PDF
        new RetrivePDFfromUrl().execute(pdfUrl);

        // For Refresh Button
        refreshBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrivePDFfromUrl().execute(pdfUrl);
                noInternetBox.setVisibility(View.GONE);
                refreshBox.setVisibility(View.GONE);
                lottieView.setVisibility(View.VISIBLE);
            }
        });



    }// onCreate Method Close Here :::::::::::::::



    // For View PDF Method
    private class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }


        @Override
        protected void onPostExecute(InputStream inputStream) {

            pdfView.fromStream(inputStream)
                    .enableSwipe(true)
                    .swipeHorizontal(true)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(false)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true)
                    .spacing(0)
                    .pageFitPolicy(FitPolicy.WIDTH)

                    .pageSnap(true)
                    .pageFling(true)

                    .onPageChange(new OnPageChangeListener() {
                        @Override
                        public void onPageChanged(int page, int pageCount) {
                            currentPageNumber.setText("পৃষ্ঠা নম্বর  "+page+"/"+pageCount);
                        }
                    })

                    .onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {
                            lottieView.setVisibility(View.GONE);
                        }
                    })
                    .onError(new OnErrorListener() {
                        @Override
                        public void onError(Throwable t) {
                            noInternet();
                        }
                    })
                    .load();

        }

    }

    // For Toolbar Method
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    // No Internet Alert Method
    private void noInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            lottieView.setVisibility(View.GONE);
            noInternetBox.setVisibility(View.VISIBLE);
            refreshBox.setVisibility(View.VISIBLE);
        }
    }




}// Public Class Close Here :::::::::::::::