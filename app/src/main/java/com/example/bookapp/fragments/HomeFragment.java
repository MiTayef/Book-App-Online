package com.example.bookapp.fragments;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bookapp.AboutActivity;
import com.example.bookapp.R;
import com.example.bookapp.helper.Constens;
import com.example.bookapp.helper.MyMethod;
import com.example.bookapp.model.CategoryAdapter;
import com.example.bookapp.model.CategoryItem;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<CategoryItem> categoryItemList = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    GridView cat_gridView;
    CardView imageSliderCardView;
    ImageSlider image_slider;
    RelativeLayout noInternetBox, refreshBox;
    RequestQueue requestQueue;
    JsonArrayRequest jsonArrayRequest;
    LottieAnimationView animationView;
    Dialog dialog;

    //:::::::::::::::For Toolbar and Navigation Menu
    Toolbar toolbar;
    NavigationView nav_View;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    //::::::::::::::::::::::::::::::::


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View MyView = inflater.inflate(R.layout.fragment_home, container, false);

        cat_gridView = MyView.findViewById(R.id.cat_gridView);
        imageSliderCardView = MyView.findViewById(R.id.imageSliderCardView);
        image_slider = MyView.findViewById(R.id.image_slider);
        animationView = MyView.findViewById(R.id.animationView);
        noInternetBox = MyView.findViewById(R.id.noInternetBox);
        refreshBox = MyView.findViewById(R.id.refreshBox);
        categoryItemList.clear();
        categoryAdapter = new CategoryAdapter(categoryItemList, getContext());
        cat_gridView.setAdapter(categoryAdapter);
        cat_gridView.setAdapter(categoryAdapter);


        //:::::::::::::::For Toolbar and Navigation Menu
        toolbar = MyView.findViewById(R.id.toolbar);
        nav_View = MyView.findViewById(R.id.nav_View);
        drawerLayout = MyView.findViewById(R.id.drawerLayout);


        // For Image Slider ::::::::::::::::::::::::::::::
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.image_slider_1, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slider_2, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slider_3, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slider_4, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slider_5, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image_slider_6, ScaleTypes.CENTER_CROP));
        image_slider.setImageList(imageList);

        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Constens.CATEGORY, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                animationView.setVisibility(View.GONE);
                try {
                    for (int x = 0; x < response.length(); x++) {
                        JSONObject jsonObject = response.getJSONObject(x);
                        String cat_img = jsonObject.getString("cat_img");
                        String cat_title = jsonObject.getString("cat_title");
                        String cat_subTitle = jsonObject.getString("cat_subtitle");
                        String cat_serverUrl = jsonObject.getString("serverUrl");
                        categoryItemList.add(new CategoryItem(cat_img, cat_title, cat_subTitle, cat_serverUrl));
                        categoryAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    animationView.setVisibility(View.GONE);
                    noInternet(getActivity());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                animationView.setVisibility(View.GONE);
                noInternet(getContext());
            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);

        checkInternetConnection(getContext());

        // For Refresh Button
        refreshBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(jsonArrayRequest);
                noInternetBox.setVisibility(View.GONE);
                refreshBox.setVisibility(View.GONE);
                imageSliderCardView.setVisibility(View.VISIBLE);
                animationView.setVisibility(View.VISIBLE);
            }
        });


        // For Exit App Features
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitConfirmationDialog();
            }
        });


        toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Drawer Click Event ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        nav_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.mHome:
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mBookReq:
                        MyMethod.openBrowser(getContext(), "https://forms.gle/zE9c7Eg7uUZWHbyW6");
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mShare:
                        MyMethod.shareApp(getContext());
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mRate:
                        final String appPackageName = getActivity().getPackageName();
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id" + appPackageName)));
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mPrivacy:
                        MyMethod.openBrowser(getContext(), "https://primecompanybangla.blogspot.com/2024/09/book-app-privacy-policy.html");
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mAbout:
                        startActivity(new Intent(getActivity(), AboutActivity.class));
                        drawerLayout.closeDrawers();
                        break;


                }

                return false;
            }
        });
        //Drawer Click Event Close Here :::::::::::::::::::::::::::::::


        return MyView;
    } //Close onCreate Method ::::::::::::::::::::


    // Check Internet Connection Method
    private void checkInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            noInternet(context);
        }
    }

    // No Internet Alert Method
    private void noInternet(Context context) {
        noInternetBox.setVisibility(View.VISIBLE);
        refreshBox.setVisibility(View.VISIBLE);
        imageSliderCardView.setVisibility(View.GONE);
    }


    // START On Back Press Method For Exit App
    private void showExitConfirmationDialog() {

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_alert_dialouge);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(getContext(), R.drawable.alert_dialouge_bg));

        Button yesButton = dialog.findViewById(R.id.yesButton);
        Button noButton = dialog.findViewById(R.id.noButton);


        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


}//Close Public Class Here :::::::::::::::::
