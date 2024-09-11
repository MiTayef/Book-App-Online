package com.example.bookapp.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.example.bookapp.ViewPDF;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    List<BookItem> itemList;
    Context context;
    private InterstitialAd interstitialAd;

    public BookAdapter(List<BookItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;

        // Initialize the Mobile Ads SDK
        MobileAds.initialize(context, initializationStatus -> {});

        // Load the first interstitial ad
        loadInterstitialAd();
    }


    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, "ca-app-pub-7795414021148606/2111923717", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd ad) {
                // Ad successfully loaded
                interstitialAd = ad;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                // Handle the error, and set the interstitialAd to null
                interstitialAd = null;
            }
        });
    }


    @NonNull
    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(context).inflate(R.layout.book_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.BookViewHolder holder, int position) {

        BookItem bookItem = itemList.get(position);

        int ItemNumber = position+1;
        holder.book_item_number.setText(""+ItemNumber);
        holder.setData(itemList.get(position));

    }//onBindViewHolder Close Here ::::::::::::::

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        TextView book_item_number, book_title, book_sub_title;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            book_item_number = itemView.findViewById(R.id.book_item_number);
            book_title = itemView.findViewById(R.id.book_title);
            book_sub_title = itemView.findViewById(R.id.book_sub_title);

        }

        void setData(BookItem bookItem){

            book_title.setText(bookItem.getTitle());
            book_sub_title.setText(bookItem.getSubTitle());

            itemView.setOnClickListener(view -> {
                if (interstitialAd != null) {
                    // Show the ad before moving to the PdfDetails activity
                    interstitialAd.show((android.app.Activity) context);
                    // Set a listener to detect when the ad is closed
                    interstitialAd.setFullScreenContentCallback(new com.google.android.gms.ads.FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Ad is dismissed, move to PdfDetails
                            moveToPdfDetails(bookItem, view);
                            // Load a new ad for the next time
                            loadInterstitialAd();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                            // If the ad fails to show, move to PdfDetails directly
                            moveToPdfDetails(bookItem, view);
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            // Set interstitialAd to null after showing to avoid reusing
                            interstitialAd = null;
                        }
                    });
                } else {
                    // If the ad is not loaded, proceed directly to PdfDetails activity
                    moveToPdfDetails(bookItem, view);
                }
            });
        }

        private void moveToPdfDetails(BookItem bookItem, View view) {
            Intent intent = new Intent(view.getContext(), ViewPDF.class);
            intent.putExtra("pdfUrl",bookItem.getPdfUrl());
            intent.putExtra("title",bookItem.getTitle());
            view.getContext().startActivity(intent);
        }
    }
}
