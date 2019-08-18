package com.dev.lungyu.homecontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dev.lungyu.homecontroller.service.FCMTools;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new FCMTools();

        loginPage();
    }

    private void loginPage(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);

        finish();
    }
}
