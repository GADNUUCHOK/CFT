package com.bignerdranch.android.centerfinancialtechnology;

import android.graphics.Color;
import android.media.Image;
import android.text.style.BackgroundColorSpan;

import java.util.UUID;

public class Pole {

    private UUID mId;
    private String mTitle;
    private Image mImage;
    private BackgroundColorSpan mColor;


    public Pole() {
        this(UUID.randomUUID());
    }
    public Pole(UUID id) {
        mId = id;
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
