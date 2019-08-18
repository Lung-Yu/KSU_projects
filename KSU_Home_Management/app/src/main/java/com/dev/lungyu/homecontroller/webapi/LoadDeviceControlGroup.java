package com.dev.lungyu.homecontroller.webapi;

import android.content.Context;
import android.util.Log;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.webapi.base.ServiceConnector;
import com.dev.lungyu.homecontroller.webapi.base.WebAPIBase;
import com.dev.lungyu.homecontroller.webapi.datamodel.DeviceGroupInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lungyu on 3/14/18.
 */

public class LoadDeviceControlGroup extends WebAPIBase {

    private static final String TAG = "LoadDeviceControlGroup";

    private List<DeviceGroupInfo> datas;

    private boolean IsControl;

    public LoadDeviceControlGroup(Context context) {
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
        return this.context.getString(R.string.web_api_url_device_control_group) + "?id="+ ((IsControl)?"1":0);
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
                item.setType(obj.getString(DeviceGroupInfo.ID));

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

    public void setControl(boolean control) {
        IsControl = control;
    }
}
