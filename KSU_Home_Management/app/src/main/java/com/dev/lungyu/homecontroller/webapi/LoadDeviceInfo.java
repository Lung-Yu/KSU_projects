package com.dev.lungyu.homecontroller.webapi;

import android.content.Context;
import android.util.Log;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.webapi.base.ServiceConnector;
import com.dev.lungyu.homecontroller.webapi.base.WebAPIBase;
import com.dev.lungyu.homecontroller.webapi.datamodel.DeviceInfo;
import com.dev.lungyu.homecontroller.webapi.datamodel.DeviceItemInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lungyu on 3/3/18.
 */

public class LoadDeviceInfo extends WebAPIBase{

    private static final String TAG = "LoadDeviceInfo";
    private String macAddress;

    private List<DeviceInfo> datas;

    public LoadDeviceInfo(Context context){
        super(context);
        this.token = "temp";
    }

    @Override
    protected JSONObject getParams() {
        JSONObject jsonParam = new JSONObject();

        try {
            jsonParam.put("mac",getMacAddress());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonParam;
    }

    @Override
    protected String getUrl() {
        return this.context.getString(R.string.web_api_url_deviceInfo) + "?mac="+getMacAddress();
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
        datas = new ArrayList<>();

        try {

            JSONArray json_response = new JSONArray(response_message);
            for(int i=0;i<json_response.length();i++){
                JSONObject obj = json_response.getJSONObject(i);
                Log.d(TAG,"object index => " + i);

//                Log.d(TAG,obj.getString(DeviceInfo.ID));
//                Log.d(TAG,obj.getString(DeviceInfo.MAC));
//                Log.d(TAG,obj.getString(DeviceInfo.TYPE));
//                Log.d(TAG,obj.getString(DeviceInfo.DATA));
//                Log.d(TAG,obj.getString(DeviceInfo.Time));
//                Log.d(TAG,obj.getString(DeviceInfo.NAME));
//                Log.d(TAG,obj.getString(DeviceInfo.UNIT));

                DeviceInfo item = new DeviceInfo();
                item.setId(obj.getString(DeviceInfo.ID));
                item.setMac(obj.getString(DeviceInfo.MAC));
                item.setType(obj.getString(DeviceInfo.TYPE));
                item.setData(obj.getString(DeviceInfo.DATA));
                item.setTime(obj.getString(DeviceInfo.Time));
                item.setName(obj.getString(DeviceInfo.NAME));
                item.setUnit(obj.getString(DeviceInfo.UNIT));

                datas.add(item);

            }
        } catch (JSONException e) {
//            e.printStackTrace();
        }
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }


    public List<DeviceInfo> getDatas() {
        return datas;
    }
}

