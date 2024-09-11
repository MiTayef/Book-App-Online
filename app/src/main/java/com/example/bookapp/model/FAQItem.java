package com.example.bookapp.model;

public class FAQItem {

    String faqTitle, faqAnswer;

    public FAQItem(String faqTitle, String faqAnswer) {
        this.faqTitle = faqTitle;
        this.faqAnswer = faqAnswer;
    }

    public String getFaqTitle() {
        return faqTitle;
    }

    public void setFaqTitle(String faqTitle) {
        this.faqTitle = faqTitle;
    }

    public String getFaqAnswer() {
        return faqAnswer;
    }

    public void setFaqAnswer(String faqAnswer) {
        this.faqAnswer = faqAnswer;
    }
}
