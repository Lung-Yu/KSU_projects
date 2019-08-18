package com.dev.lungyu.find_rip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.dev.lungyu.find_rip.view.MapView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        FrameLayout layout = (FrameLayout) findViewById(R.id.map_layout);
        MapView view = new MapView(this,10,10,5,5);
        layout.addView(view);
    }
}

