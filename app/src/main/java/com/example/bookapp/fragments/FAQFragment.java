package com.example.bookapp.fragments;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

import android.app.Dialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.bookapp.R;
import com.example.bookapp.model.FAQAdapter;
import com.example.bookapp.model.FAQItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class FAQFragment extends Fragment {

    RecyclerView faqRecyclerView;
    List<FAQItem> faqItemList = new ArrayList<>();
    FAQAdapter faqAdapter;
    Dialog dialog;
    private LinearLayout adContainerViewFaq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_f_a_q, container, false);

        faqRecyclerView = myView.findViewById(R.id.faqRecyclerView);
        adContainerViewFaq = myView.findViewById(R.id.adContainerViewFaq);



        // Create a new ad view.
        AdView adView = new AdView(getContext());
        adView.setAdUnitId("ca-app-pub-7795414021148606/3713328734");
        adView.setAdSize(AdSize.BANNER);
        // Replace ad container with new ad view.
        adContainerViewFaq.removeAllViews();
        adContainerViewFaq.addView(adView);
        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        faqRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        faqAdapter = new FAQAdapter(faqItemList, getContext());
        faqRecyclerView.setAdapter(faqAdapter);

        faqItemList.add(new FAQItem("বাংলাদেশী উপন্যাসগুলোর মধ্যে কোনগুলো অবশ্যপাঠ্য?", "উত্তর: \"নন্দিত নরকে\" - হুমায়ূন আহমেদ, \"লজ্জা\" - তসলিমা নাসরিন, এবং \"অরণ্যক\" - বিভূতিভূষণ বন্দ্যোপাধ্যায়।"));
        faqItemList.add(new FAQItem("কোন কোন বাংলা কবিদের লেখা পড়া উচিত?", "উত্তর: কাজী নজরুল ইসলাম, রবীন্দ্রনাথ ঠাকুর, এবং জীবনানন্দ দাশ বাংলা সাহিত্যের প্রধান কবি।"));
        faqItemList.add(new FAQItem("হুমায়ূন আহমেদের সেরা কাজ কোনটি?", "উত্তর: তাঁর \"মিসির আলি\" সিরিজ এবং \"হিমু\" সিরিজ অত্যন্ত জনপ্রিয়।"));
        faqItemList.add(new FAQItem("বাংলাদেশ মুক্তিযুদ্ধের উপর ভিত্তি করে কোন বইগুলো পড়া উচিত?", "উত্তর: \"একাত্তরের দিনগুলি\" - জাহানারা ইমাম, \"এ গোল্ডেন এজ\" - তাহমিমা আনাম, এবং \"আগুনের পরশমণি\" - হুমায়ূন আহমেদ।"));
        faqItemList.add(new FAQItem("বর্তমান সময়ের কোন বাংলাদেশী লেখকদের পড়া উচিত?", "উত্তর: তাহমিমা আনাম, জাফর ইকবাল, এবং আনিসুল হক বর্তমানের উল্লেখযোগ্য লেখক।"));
        faqItemList.add(new FAQItem("বাংলাদেশের ইতিহাস নিয়ে সেরা বইগুলো কোনগুলো?", "উত্তর: \"অসমাপ্ত আত্মজীবনী\" - শেখ মুজিবুর রহমান এবং \"দ্য ব্লাড টেলিগ্রাম\" - গ্যারি জে. বাস।"));
        faqItemList.add(new FAQItem("ঢাকাকে বুঝতে হলে কোন বইগুলো পড়া উচিত?", "উত্তর: \"ঢাকা: অ্যান আরবান রিডার\" এবং \"গৌরীপুর জংশন\" - হুমায়ূন আহমেদ।"));
        faqItemList.add(new FAQItem("আন্তর্জাতিকভাবে স্বীকৃত কোন কোন বাংলাদেশী লেখক আছেন?", "উত্তর: তাহমিমা আনাম, মনিকা আলী, এবং জিয়া হায়দার রহমান আন্তর্জাতিকভাবে পরিচিত।"));
        faqItemList.add(new FAQItem("কোন বাংলাদেশী শিশুসাহিত্য জনপ্রিয়?", "উত্তর: \"ঠাকুরমার ঝুলি\" এবং \"মীনা\" সিরিজ শিশুদের মধ্যে জনপ্রিয়।"));
        faqItemList.add(new FAQItem("বাংলা সাহিত্যের কোন অনুবাদিত কাজগুলো জনপ্রিয়?", "উত্তর: \"পদ্মা নদীর মাঝি\" - মানিক বন্দ্যোপাধ্যায় এবং \"চোখের বালি\" - রবীন্দ্রনাথ ঠাকুর।"));

        faqItemList.add(new FAQItem("বাংলাদেশী সমাজে সাহিত্যের ভূমিকা কী?", "উত্তর: সাহিত্য বাংলাদেশী সংস্কৃতির কেন্দ্রে, যা সামাজিক ও রাজনৈতিক বিষয়গুলিকে প্রতিফলিত করে।"));
        faqItemList.add(new FAQItem("বাংলাদেশী নারী লেখকদের কোন কোন বই গুরুত্বপূর্ণ?", "উত্তর: \"লজ্জা\" - তসলিমা নাসরিন এবং \"এ গোল্ডেন এজ\" - তাহমিমা আনাম।"));
        faqItemList.add(new FAQItem("কোন কোন বাংলা গোয়েন্দা উপন্যাস পড়া উচিত?", "উত্তর: হুমায়ূন আহমেদের \"মিসির আলি\" সিরিজ এবং সত্যজিৎ রায়ের \"ফেলুদা\" সিরিজ।"));
        faqItemList.add(new FAQItem("বাংলাদেশের গ্রামীণ জীবনকে কেন্দ্র করে কোন কোন বই পড়া উচিত?", "উত্তর: \"পদ্মা নদীর মাঝি\" - মানিক বন্দ্যোপাধ্যায় এবং \"তিতাস একটি নদীর নাম\" - অদ্বৈত মল্লবর্মণ।"));
        faqItemList.add(new FAQItem("বাংলাদেশী কবিতা সম্পর্কে ভালো ধারণা পেতে হলে কোন বইগুলো পড়া উচিত?", "উত্তর: কাজী নজরুল ইসলামের \"বিদ্রোহী\" এবং জীবনানন্দ দাশের \"বনলতা সেন\" দিয়ে শুরু করতে পারেন।"));
        faqItemList.add(new FAQItem("রবীন্দ্রনাথ ঠাকুরের সবচেয়ে বিখ্যাত কাজ কোনটি?", "উত্তর: \"গীতাঞ্জলি\", \"গোরা\", এবং \"শেষের কবিতা\" তাঁর সবচেয়ে উল্লেখযোগ্য কাজ।"));
        faqItemList.add(new FAQItem("বাংলাদেশের কোন কোন সাহিত্য উৎসব জনপ্রিয়?", "উত্তর: একুশে বইমেলা এবং ঢাকা লিট ফেস্ট সবচেয়ে জনপ্রিয়।"));
        faqItemList.add(new FAQItem("বাংলাদেশের কোন কোন ছোটগল্প লেখক গুরুত্বপূর্ণ?", "উত্তর: সৈয়দ শামসুল হক, সেলিনা হোসেন, এবং হাসান আজিজুল হক।"));
        faqItemList.add(new FAQItem("বাংলাদেশী কোন কোন আত্মজীবনী জনপ্রিয়?", "উত্তর: \"অসমাপ্ত আত্মজীবনী\" - শেখ মুজিবুর রহমান এবং \"আমার ছেলেবেলা\" - হুমায়ূন আহমেদ।"));
        faqItemList.add(new FAQItem("বাংলাদেশী কোন কোন বই চলচ্চিত্রে রূপান্তরিত হয়েছে?", "উত্তর: \"শ্যামল ছায়া\" - হুমায়ূন আহমেদ এবং \"মাটির ময়না\" - তারেক মাসুদ।"));

        faqItemList.add(new FAQItem("বাংলাদেশের সায়েন্স ফিকশন সাহিত্যের কোন কাজগুলো জনপ্রিয়?", "উত্তর: \"আমি টপু\" - মুহম্মদ জাফর ইকবাল এবং \"তোমাদের জন্য ভালোবাসা\" - হুমায়ূন আহমেদ।"));
        faqItemList.add(new FAQItem("বাংলাদেশী সাহিত্যের কোন প্রবন্ধগুলি প্রভাবশালী?", "উত্তর: জহির রায়হান এবং আবুল মনসুর আহমেদের প্রবন্ধগুলি অত্যন্ত শ্রদ্ধেয়।"));
        faqItemList.add(new FAQItem("বাংলা সাহিত্যের জনক হিসেবে কাকে ধরা হয়?", "উত্তর: রবীন্দ্রনাথ ঠাকুরকে আধুনিক বাংলা সাহিত্যের জনক বলা হয়।"));
        faqItemList.add(new FAQItem("বাংলাদেশী রাজনীতি নিয়ে কোন কোন বই সেরা?", "উত্তর: \"দ্য ব্লাড টেলিগ্রাম\" - গ্যারি জে. বাস এবং \"বাংলাদেশ: এ লেগাসি অফ ব্লাড\" - অ্যান্থনি মাসকারেনহাস।"));
        faqItemList.add(new FAQItem("বাংলাদেশী সংস্কৃতি নিয়ে কোন কোন বই পড়া উচিত?", "উত্তর: \"বাংলাদেশ: রিফ্লেকশনস অন দ্য ওয়াটার\" - জেমস জে. নভাক এবং \"বাংলাদেশের সংস্কৃতি ও রীতিনীতি\" - সৈয়দুর রহমান।"));
        faqItemList.add(new FAQItem("বঙ্গবিভাগ নিয়ে কোন কোন বই পড়া উচিত?", "উত্তর: \"বেঙ্গল ডিভাইডেড\" - জয়া চ্যাটার্জি এবং \"দ্য শ্যাডো লাইনস\" - অমিতাভ ঘোষ।"));
        faqItemList.add(new FAQItem("বাংলাদেশের সবচেয়ে বেশি বিক্রি হওয়া বই কোনগুলো?", "উত্তর: হুমায়ূন আহমেদের \"হিমু\" এবং \"মিসির আলি\" সিরিজ সর্বাধিক বিক্রিত।"));
        faqItemList.add(new FAQItem("বাংলাদেশী নারীদের জীবন নিয়ে লেখালেখি করা কোন লেখকরা পরিচিত?", "উত্তর: সেলিনা হোসেন, নাসরীন জাহান, এবং তসলিমা নাসরিন প্রায়শই তাদের লেখায় নারীদের জীবন নিয়ে আলোচনা করেন।"));
        faqItemList.add(new FAQItem("লালন ফকিরের গুরুত্ব কী?", "উত্তর: লালন ফকির বাংলাদেশের একজন গুরুত্বপূর্ণ সাংস্কৃতিক ব্যক্তিত্ব এবং তার গান ও দর্শন বাংলা সংস্কৃতিতে গভীর প্রভাব ফেলেছে।"));
        faqItemList.add(new FAQItem("মুক্তিযুদ্ধের সময়কার কোন কোন বাংলাদেশী উপন্যাস পড়া উচিত?", "উত্তর: \"আগুনের পরশমণি\" - হুমায়ূন আহমেদ এবং \"এ গোল্ডেন এজ\" - তাহমিমা আনাম মুক্তিযুদ্ধের উপর ভিত্তি করে রচিত।"));

        // For Exit App Features
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitConfirmationDialog();
            }
        });



        return myView;
    } // onCreateView End Here :::::::::



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





} // Public Class End Here :::::::::::::