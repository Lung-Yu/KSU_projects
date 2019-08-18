package com.dev.lungyu.homecontroller.webapi;

import android.content.Context;
import android.util.Log;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.webapi.base.ServiceConnector;
import com.dev.lungyu.homecontroller.webapi.base.WebAPIBase;
import com.dev.lungyu.homecontroller.webapi.datamodel.ConditionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lungyu on 3/14/18.
 */

public class DelAlertCondition extends WebAPIBase {
    private static final String TAG = "DelAlertCondition";

    private String alert_id;

    public DelAlertCondition(Context context){
        super(context);
        this.token = "temp";
    }

    @Override
    protected JSONObject getParams() {
        JSONObject jsonParam = new JSONObject();

        return jsonParam;
    }

    @Override
    protected String getUrl() {
        return this.context.getString(R.string.web_api_url_del_alert) + "?id=" + alert_id;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        service_connect();
        parser_response();
        return null;
    }

    private void service_connect(){
        ServiceConnector connector = new ServiceConnector(getUrl());
        connector.connectGetByToken(this.token);
        response_message = connector.getResponse();
    }

    private void parser_response(){

    }

    public String getAlert_id() {
        return alert_id;
    }

    public void setAlert_id(String alert_id) {
        this.alert_id = alert_id;
    }
}
