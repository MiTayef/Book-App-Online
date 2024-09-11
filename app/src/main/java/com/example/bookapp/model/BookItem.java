package com.example.bookapp.model;

public class BookItem {

    String title, subTitle, pdfUrl;

    public BookItem(String title, String subTitle, String pdfUrl) {
        this.title = title;
        this.subTitle = subTitle;
        this.pdfUrl = pdfUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
