package com.example.bookapp.model;

public class CategoryItem {

    String cat_img, cat_title, cat_subtitle;
    String serverUrl;

    public CategoryItem(String cat_img, String cat_title, String cat_subtitle, String serverUrl) {

        this.cat_img = cat_img;
        this.cat_title = cat_title;
        this.cat_subtitle = cat_subtitle;
        this.serverUrl = serverUrl;
    }

    public String getCat_img() {
        return cat_img;
    }

    public void setCat_img(String cat_img) {
        this.cat_img = cat_img;
    }

    public String getCat_title() {
        return cat_title;
    }

    public void setCat_title(String cat_title) {
        this.cat_title = cat_title;
    }

    public String getCat_subtitle() {
        return cat_subtitle;
    }

    public void setCat_subtitle(String cat_subtitle) {
        this.cat_subtitle = cat_subtitle;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}// CategoryItem End Here  ::::::::::::::
