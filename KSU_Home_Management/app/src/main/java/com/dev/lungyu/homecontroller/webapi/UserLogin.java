package com.dev.lungyu.homecontroller.webapi;

import android.content.Context;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.tools.dialog.ValidProcessDialog;
import com.dev.lungyu.homecontroller.webapi.base.ServiceConnector;
import com.dev.lungyu.homecontroller.webapi.base.WebAPIBase;
import com.dev.lungyu.homecontroller.webapi.base.WebAPITools;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lungyu on 2/24/18.
 */

public class UserLogin extends WebAPIBase {
    private String account;
    private String password;

    public UserLogin(Context context){
        super(context);
        this.token = "";
        myProcessDialog = new ValidProcessDialog(context);
    }

    @Override
    protected JSONObject getParams(){
        JSONObject jsonParam = new JSONObject();

        try {
            jsonParam.put("user", this.account);
            jsonParam.put("pwd", this.password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonParam;
    }

    @Override
    protected String getUrl() {
        String url = this.context.getString(R.string.web_api_url_login)+"?user="+account + "&pwd="+password;
        return url;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        service_connect();
        parser_response();

        return null;
    }

    private void service_connect(){
        ServiceConnector connector = new ServiceConnector(getUrl());
        connector.connect("acc="+getAccount()+"&pwd="+getPassword());
        response_message = connector.getResponse();
    }

    private void parser_response(){
//        try {
//            JSONObject json_response = new JSONObject(response_message);
//            this.token = json_response.getString("Token");
            this.token = response_message;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    public void setAccount(String account){
        this.account = account;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getAccount(){
        return this.account;
    }

    public String getPassword(){
        return this.password;
    }

}
