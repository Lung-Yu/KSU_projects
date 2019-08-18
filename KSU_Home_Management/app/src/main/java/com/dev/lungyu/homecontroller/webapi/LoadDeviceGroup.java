package com.dev.lungyu.homecontroller.webapi;

import android.content.Context;
import android.util.Log;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.webapi.base.ServiceConnector;
import com.dev.lungyu.homecontroller.webapi.base.WebAPIBase;
import com.dev.lungyu.homecontroller.webapi.datamodel.DeviceGroupInfo;
import com.dev.lungyu.homecontroller.webapi.datamodel.DeviceItemInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lungyu on 3/5/18.
 */

public class LoadDeviceGroup extends WebAPIBase {

    private static final String TAG = "LoadDeviceGroup";

    private List<DeviceGroupInfo> datas;

    private String macAddress;
    public LoadDeviceGroup(Context context) {
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
        return this.context.getString(R.string.web_api_url_device_group) + "?mac="+getMacAddress();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        service_connect();
        parser_response();
        return null;
    }

    private void service_connect() {
        ServiceConnector connector = new ServiceConnector(getUrl());
        connector.connectGetByToken(this.token);
        response_message = connector.getResponse();
    }

    private void parser_response() {
        datas = new ArrayList<>();
        try {
            JSONArray json_response = new JSONArray(response_message);
            for (int i = 0; i < json_response.length(); i++) {
                JSONObject obj = json_response.getJSONObject(i);
                Log.d(TAG, "object index => " + i);

                DeviceGroupInfo item = new DeviceGroupInfo();
                item.setId(obj.getString(DeviceGroupInfo.ID));
                item.setMac(obj.getString(DeviceGroupInfo.MAC));
                item.setType(obj.getString(DeviceGroupInfo.TYPE));
                item.setTime(obj.getString(DeviceGroupInfo.TIME));

                datas.add(item);
            }
//            this.token = json_response.getString("Token");
        } catch (JSONException e) {
//            e.printStackTrace();
        }
    }

    public List<DeviceGroupInfo> getDatas() {
        return this.datas;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}