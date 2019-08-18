package com.dev.lungyu.homecontroller.webapi.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.dev.lungyu.homecontroller.tools.dialog.IDialog;
import com.dev.lungyu.homecontroller.tools.dialog.LoadingProcessDialog;

import org.json.JSONObject;

/**
 * Created by lungyu on 2/24/18.
 */

public abstract class WebAPIBase extends AsyncTask<Void,Void,Void> {
    protected int http_code;
    protected String response_message;
    protected String token;

    protected Context context;

    protected WebAPIBase(Context context){
        this.context = context;

        if(context!=null)
            myProcessDialog = new LoadingProcessDialog(context);
    }

    private WebApiCallBack callBackFunc;

    protected WebAPIBase(){
        callBackFunc = null;
    }
    protected WebAPIBase(WebApiCallBack callBackFunc){
        this.callBackFunc = callBackFunc;
    }

    protected abstract JSONObject getParams() ;
    protected abstract String  getUrl();


    protected IDialog myProcessDialog;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(myProcessDialog!=null)
            myProcessDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(myProcessDialog!=null)
            myProcessDialog.dismiss();

        if(callBackFunc != null){
            callBackFunc.onProcessCallBack();
        }
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
        if(myProcessDialog!=null)
            myProcessDialog.dismiss();
    }

    protected void service_get_connect(){
        ServiceConnector connector = new ServiceConnector(getUrl());
        connector.connectGetByToken(token);
        response_message = connector.getResponse();
    }

    public void setCallBackFunc(WebApiCallBack func){
        this.callBackFunc = func;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
