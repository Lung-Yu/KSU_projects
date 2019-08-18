package com.dev.lungyu.homecontroller.webapi;

import android.content.Context;
import android.util.Log;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.webapi.base.ServiceConnector;
import com.dev.lungyu.homecontroller.webapi.base.WebAPIBase;
import com.dev.lungyu.homecontroller.webapi.datamodel.ControlDeviceInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lungyu on 3/3/18.
 */

public class LoadControlDevices extends WebAPIBase {

    private static final String TAG = "LoadControlDevices";

    private List<ControlDeviceInfo> datas;

    public LoadControlDevices(Context context){
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
        return this.context.getString(R.string.web_api_url_control_list);
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

                ControlDeviceInfo item = new ControlDeviceInfo();
                item.setSensorName(obj.getString(ControlDeviceInfo.SENSOR_NAME));
                item.setDeviceName(obj.getString(ControlDeviceInfo.DEVICE_NAME));
                item.setSensorId(obj.getString(ControlDeviceInfo.SENSOR_ID));
                item.setData(obj.getString(ControlDeviceInfo.DATA));
                //item.setTime(obj.getString(ControlDeviceInfo.TIME));
                item.setDeviceId(obj.getString(ControlDeviceInfo.DEVICE_ID));

                datas.add(item);
            }
        } catch (JSONException e) {
//            e.printStackTrace();
        }
    }

    public List<ControlDeviceInfo> getDatas(){
        return this.datas;
    }
}
