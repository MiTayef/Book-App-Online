package com.example.bookapp.helper;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public class MyMethod {

   //Open Browser Method
    public static void openBrowser(Context context, String websiteUrl){
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl)));
    }

    //Share App Method
    public static void shareApp(Context context){
        final String appPackageName = context.getPackageName();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
        intent.putExtra(Intent.EXTRA_TEXT, "Download Now: https://play.google.com/store/apps/details?id="+appPackageName);
        context.startActivity(Intent.createChooser(intent, "Share Via"));
    }


} // Public Class Close Here :::::::::::::
