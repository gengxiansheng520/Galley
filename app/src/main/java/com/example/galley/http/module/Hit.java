package com.example.galley.http.module;

import android.os.Parcel;
import android.os.Parcelable;

public class Hit implements Parcelable {
    private String pageURL;
    private String previewURL;
    private String webformatURL;
    private String largeImageURL;
    private int id;

    @Override
    public String toString() {
        return "Hit{" +
                "pageURL='" + pageURL + '\'' +
                ", previewURL='" + previewURL + '\'' +
                ", webformatURL='" + webformatURL + '\'' +
                ", largeImageURL='" + largeImageURL + '\'' +
                ", id=" + id +
                '}';
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
