package com.example.bookapp;

import static android.app.PendingIntent.getActivity;

import  android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookapp.fragments.FAQFragment;
import com.example.bookapp.fragments.WriteFragment;
import com.example.bookapp.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      BottomNav();
      bottom_nav.setSelectedItemId(R.id.nav_home);


    }// OnCreateMethod End Here ::::::::::::::


    private void BottomViewChange(Fragment fragment){
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }//BottomViewChange Method End Here :::::::::::::

    private void BottomNav(){

        bottom_nav = findViewById(R.id.bottom_nav);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.nav_home:
                        BottomViewChange(new HomeFragment());
                        break;

                    case R.id.nav_write:
                        BottomViewChange(new WriteFragment());
                        break;

                    case R.id.nav_faq:
                        BottomViewChange(new FAQFragment());
                        break;

                }

                return true;
            }
        });

    }// BottomNav Method Close Here :::::::::::::::


}// Public Class End Here :::::::::::::