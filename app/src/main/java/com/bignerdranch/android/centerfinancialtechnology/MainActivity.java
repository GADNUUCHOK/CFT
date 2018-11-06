package com.bignerdranch.android.centerfinancialtechnology;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends SingleFragmentActivity {

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

    /**public static Intent newIntent(Context packageContext, ) {
        intent.putExtra();
        return intent;
    }*/
}
