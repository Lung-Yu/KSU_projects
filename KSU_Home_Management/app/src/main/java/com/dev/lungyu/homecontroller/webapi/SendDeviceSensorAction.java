package com.dev.lungyu.homecontroller.webapi;

import android.content.Context;
import android.util.Log;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.webapi.base.ServiceConnector;
import com.dev.lungyu.homecontroller.webapi.base.WebAPIBase;
import com.dev.lungyu.homecontroller.webapi.datamodel.DeviceInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lungyu on 3/3/18.
 */

public class SendDeviceSensorAction extends WebAPIBase {

    public static final String TAG = "SendDeviceSensorAction";

    private String macAddress;
    private String deviceId;
    private String action;

    public SendDeviceSensorAction(Context context){
        super(context);
        this.token = "temp";
    }

    @Override
    protected JSONObject getParams() {
        JSONObject jsonParam = new JSONObject();

//        try {
//            jsonParam.put("user", token);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return jsonParam;
    }

    //http://120.114.134.135/webapi/control_device.php?mac=5C%3ACF%3A7F%3A10%3AA8%3AC1&status=1&id=1
    @Override
    protected String getUrl() {
        return this.context.getString(R.string.web_api_url_control_action) + "?mac="+macAddress+"&status="+action+"&id="+deviceId;
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
        Log.d(TAG,response_message);
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = String.valueOf(deviceId);
    }

    public void setAction(boolean On) {
        this.action = (On)?"1":"0";
    }

    public void setAction(String action){
        this.action = action;
    }
}
