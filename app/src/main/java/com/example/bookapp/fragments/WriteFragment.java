package com.example.bookapp.fragments;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookapp.R;

public class WriteFragment extends Fragment {

    private EditText titleEditText, subtitleEditText, detailsEditText, pdfUrlEditText, storyEditText, userEmailEditText;
    private Button submitButton;
    Dialog dialog;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write, container, false);

        titleEditText = view.findViewById(R.id.titleEditText);
        subtitleEditText = view.findViewById(R.id.subtitleEditText);
        userEmailEditText = view.findViewById(R.id.userEmailEditText);
        detailsEditText = view.findViewById(R.id.detailsEditText);
        pdfUrlEditText = view.findViewById(R.id.pdfUrlEditText);
        storyEditText = view.findViewById(R.id.storyEditText);
        submitButton = view.findViewById(R.id.submitButton);



        // For Exit App Features
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitConfirmationDialog();
            }
        });



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    sendEmail();
                    Toast.makeText(getActivity(), "আমাদের মেইল সেন্ড করুন।", Toast.LENGTH_LONG).show();

                    //Create A Alert Dialog For User:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

                } else {
                    Toast.makeText(getActivity(), "ইমেইল পাঠানোর আগে সঠিক ভাবে খালি ঘর পূরণ করুন।", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    } // Close On Create Method




    private void sendEmail() {
        // Get user input from EditText fields
        String title = titleEditText.getText().toString();
        String subTitle = subtitleEditText.getText().toString();
        String email = userEmailEditText.getText().toString();
        String details = detailsEditText.getText().toString();
        String pdfUrl = pdfUrlEditText.getText().toString();
        String story = storyEditText.getText().toString();

        // Define the email content
        String subject = "পছন্দের বই যুক্ত করুন!";
        String body = "বইয়ের নাম: " + title + "\nলেখকের নাম: " + subTitle + "\nইমেইল: " + email + "\nসংক্ষেপে: " + details + "\nপিডিএফ লিংক: " + pdfUrl + "\nব্যক্তিগত মতামত: " + story;

        // Create an email Intent
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");  // MIME type for email
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info.waneex@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Confirm my book", Toast.LENGTH_SHORT).show();
        }
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




    //For Empty box and Invalid Info Checker
    private void setupListeners() {
        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) {
                    titleEditText.setError("বই এর নাম ৫০ অক্ষরের ভিতরে লিখুন।");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        subtitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 50) {
                    subtitleEditText.setError("লেখকের নাম ৫০ অক্ষরের ভিতরে লিখুন।");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        detailsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 300) {
                    detailsEditText.setError("সংক্ষেপে লেখার জন্য ৩০০ অক্ষর পর্যন্ত ব্যবহার করতে পারবেন ।");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        pdfUrlEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().endsWith(".pdf")) {
                    pdfUrlEditText.setError("PDF URL এর লিংক অবশ্যই .PDF এর লিংক হতে হবে।");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

        storyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 1000) {
                    storyEditText.setError("আপনার বিস্তারিত মতামত এক হাজার শব্দের ভিতরে লিখুন।");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


    }

    private boolean validateFields() {
        boolean valid = true;

        if (titleEditText.getText().toString().trim().isEmpty()) {
            titleEditText.setError("বই এর নাম দিন");
            valid = false;
        }

        if (subtitleEditText.getText().toString().trim().isEmpty()) {
            subtitleEditText.setError("লেখকের নাম দিন");
            valid = false;
        }

        if (detailsEditText.getText().toString().trim().isEmpty()) {
            detailsEditText.setError("সংক্ষেপে লিখুন");
            valid = false;
        }

        if (userEmailEditText.getText().toString().trim().isEmpty()) {
            userEmailEditText.setError("ইমেইল দিন");
            valid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmailEditText.getText().toString().trim()).matches()) {
            userEmailEditText.setError("বৈধ ইমেইল এড্রেস লিখুন");
            valid = false;
        }

        return valid;
    }



}