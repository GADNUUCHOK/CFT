package com.bignerdranch.android.centerfinancialtechnology;

import android.media.Image;

import java.util.UUID;

public class Pole {

    private UUID mId;
    private String mTitle;
    private Image mImage;

    public Pole() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public Image getImage() { return mImage; }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setImage(Image image) {
        this.mImage = image;
    }
}
