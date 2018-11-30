package com.bignerdranch.android.centerfinancialtechnology;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends SingleFragmentActivity {

    private static final String IMAGE_ID = "com.bignerdranch.android.centerfinancialtechnology.image_id";
    private int mCurrentIndex = 0;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected Fragment createFragment() {
        return new ScalingFragment();
    }
}
