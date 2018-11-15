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

    private static final String KEY_INDEX = "index";
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**if (savedInstanceState != null) {
            Bitmap bitmap = savedInstanceState.getParcelable("image");
            ImageView.setImageBitmap(bitmap);
        }*/
    }
    @Override
    protected Fragment createFragment() {
        return new ScalingFragment();
    }

    /**@Override
    protected void onSaveInstanceState(Bundle outState) {
        BitmapDrawable drawable = (BitmapDrawable) ImageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        outState.putParcelable("image", bitmap);
        super.onSaveInstanceState(outState);
    }*/

    /**public static Intent newIntent(Context packageContext, ) {
        intent.putExtra();
        return intent;
    }*/
}
