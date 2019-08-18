package com.dev.lungyu.find_rip;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dev.lungyu.find_rip.util.PermissionManagement;

import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class SplashActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //direct();

        checkAllowAllPermission();

    }

    private static final int REQUEST_CODE_ASK = 1055;


    private void checkAllowAllPermission(){
        PermissionManagement permissionManagement = new PermissionManagement(this);
        List<String> list = permissionManagement.getNotAllowPermission();

        if(list.size() == 0){
            directActivity();
            return;
        }

        String[] reqs = new String[list.size()];
        for(int i=0;i<reqs.length;i++)
            reqs[i] = list.get(i);


        ActivityCompat.requestPermissions(
                this,
                reqs,
                REQUEST_CODE_ASK
        );
    }

    private void directActivity(){
        //Intent intent = new Intent(this,LoginActivity.class);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        switch (requestCode){
            case REQUEST_CODE_ASK:
                checkAllowAllPermission();
                break;

            case REQUEST_READ_CONTACTS:
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //populateAutoComplete();
                }
                break;

        }
    }
}
