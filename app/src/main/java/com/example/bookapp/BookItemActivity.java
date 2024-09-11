package com.example.bookapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookapp.model.BookAdapter;
import com.example.bookapp.model.BookItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookItemActivity extends AppCompatActivity {

    RecyclerView home_recyclerView;
    List<BookItem> bookItemList = new ArrayList<>();
    BookAdapter bookAdapter;
    RelativeLayout noInternetBox, refreshBox;
    LottieAnimationView animationView;
    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;
    private LinearLayout adContainerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_item);

        Bundle bundle = getIntent().getExtras();
        String ServerUrl = bundle.getString("ServerUrl");
        String title = bundle.getString("title");

        //For ID's Initialize ::::::::::::::::::::::::::::::::::
        TextView bookItemTitle = findViewById(R.id.bookItemTitle);
        home_recyclerView = findViewById(R.id.home_recyclerView);
        animationView = findViewById(R.id.animationView);
        noInternetBox = findViewById(R.id.noInternetBox);
        refreshBox = findViewById(R.id.refreshBox);
        adContainerView = findViewById(R.id.adContainerView);
        home_recyclerView.setLayoutManager(new LinearLayoutManager(BookItemActivity.this, RecyclerView.VERTICAL, false));
        bookItemTitle.setText(title);
        bookAdapter = new BookAdapter(bookItemList, BookItemActivity.this);
        home_recyclerView.setAdapter(bookAdapter);



        // Create a new ad view.
        AdView adView = new AdView(BookItemActivity.this);
        adView.setAdUnitId("ca-app-pub-7795414021148606/3713328734");
        adView.setAdSize(AdSize.BANNER);
        // Replace ad container with new ad view.
        adContainerView.removeAllViews();
        adContainerView.addView(adView);
        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



        // Get Data From Server
        getData(ServerUrl);

        //======================================
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //======================================


        // For Refresh Button
        refreshBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue = Volley.newRequestQueue(BookItemActivity.this);
                requestQueue.add(jsonArrayRequest);
                noInternetBox.setVisibility(View.GONE);
                refreshBox.setVisibility(View.GONE);
            }
        });


    } // Close OnCreate Method :::::::::::::


    // For Collect BookItemList From Server
    private void getData(String ServerUrl) {

        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ServerUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                animationView.setVisibility(View.GONE);

                try {

                    for (int x = 0; x < response.length(); x++) {

                        JSONObject jsonObject = response.getJSONObject(x);
                        String title = jsonObject.getString("title");
                        String subTitle = jsonObject.getString("subTitle");
                        String pdfUrl = jsonObject.getString("pdfUrl");

                        bookItemList.add(new BookItem(title, subTitle, pdfUrl));
                        bookAdapter.notifyDataSetChanged();

                    }

                } catch (Exception e) {

                    animationView.setVisibility(View.GONE);
                    noInternet();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                animationView.setVisibility(View.GONE);
                noInternet();

            }
        });

        requestQueue = Volley.newRequestQueue(BookItemActivity.this);
        requestQueue.add(jsonArrayRequest);

    }

    // For Back Button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // No Internet Alert Method
    private void noInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            noInternetBox.setVisibility(View.VISIBLE);
            refreshBox.setVisibility(View.VISIBLE);
        }
    }


} // Close Public Class :::::::::::::