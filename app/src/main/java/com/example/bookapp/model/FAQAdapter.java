package com.example.bookapp.model;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.FAQViewHolder> {

    List<FAQItem> faqItemList;
    Context context;

    public FAQAdapter(List<FAQItem> faqItemList, Context context) {
        this.faqItemList = faqItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public FAQAdapter.FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FAQViewHolder(LayoutInflater.from(context).inflate(R.layout.faq_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FAQAdapter.FAQViewHolder holder, int position) {

        FAQItem faqItem = faqItemList.get(position);

        holder.setMyText(faqItem);


        holder.clickedLayout.setOnClickListener(v -> {
            if (holder.questionAnswer.getVisibility() == View.GONE){
                TransitionManager.beginDelayedTransition(holder.motherLayout, new AutoTransition());
                holder.questionAnswer.setVisibility(View.VISIBLE);
                holder.arrow.setImageResource(R.drawable.drop_up_icon);
            } else {
                TransitionManager.beginDelayedTransition(holder.motherLayout, new AutoTransition());
                holder.questionAnswer.setVisibility(View.GONE);
                holder.arrow.setImageResource(R.drawable.drop_down_icon);
            }

        });

    }

    @Override
    public int getItemCount() {
        return faqItemList.size();
    }

    public class FAQViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView motherLayout;
        RelativeLayout clickedLayout;
        TextView questionTitle, questionAnswer;
        ImageView arrow;

        public FAQViewHolder(@NonNull View itemView) {
            super(itemView);

            motherLayout = itemView.findViewById(R.id.motherLayout);
            clickedLayout = itemView.findViewById(R.id.clickedLayout);
            questionTitle = itemView.findViewById(R.id.questionTitle);
            questionAnswer = itemView.findViewById(R.id.questionAnswer);
            arrow = itemView.findViewById(R.id.arrow);

        }

        void setMyText(FAQItem faqItem){
            questionTitle.setText(faqItem.getFaqTitle());
            questionAnswer.setText(faqItem.getFaqAnswer());
        }


    }




}// FAQAdapter End Here ::::::::::::::
