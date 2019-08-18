package com.dev.lungyu.homecontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.lungyu.homecontroller.webapi.UserLogin;
import com.dev.lungyu.homecontroller.webapi.base.WebApiCallBack;

public class LoginActivity extends AppCompatActivity {

    private EditText editAccount;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editAccount = (EditText) findViewById(R.id.edit_account);
        editPassword = (EditText) findViewById(R.id.edit_password);

    }

    public void onRegister(View view) {
        Toast.makeText(this,"register",Toast.LENGTH_SHORT).show();
    }

    public void onLogin(View view) {

        final UserLogin service = new UserLogin(this);
        service.setAccount(editAccount.getText().toString());
        service.setPassword(editPassword.getText().toString());
        service.setCallBackFunc(new WebApiCallBack() {
            @Override
            public void onProcessCallBack() {
                if(service.getToken().isEmpty()){
                    loginError();
                }else{
                    mainPage();
                }
            }
        });
        service.execute();
    }

    private void mainPage(){
        Toast.makeText(this,"login",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void loginError(){
        Toast.makeText(this,"login error",Toast.LENGTH_SHORT).show();
    }
}
