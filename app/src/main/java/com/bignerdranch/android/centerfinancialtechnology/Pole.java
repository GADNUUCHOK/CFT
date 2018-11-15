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

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

    public BackgroundColorSpan getColor() { return mColor; }

    public void setColor(BackgroundColorSpan backgroundColorSpan) {
        this.mColor = backgroundColorSpan; }
}
