package com.dev.lungyu.homecontroller.webapi;

import android.content.Context;
import android.util.Log;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.tools.interpreter.DeviceInterpreter;
import com.dev.lungyu.homecontroller.webapi.base.ServiceConnector;
import com.dev.lungyu.homecontroller.webapi.base.WebAPIBase;
import com.dev.lungyu.homecontroller.webapi.datamodel.ConditionModel;
import com.dev.lungyu.homecontroller.webapi.datamodel.DeviceItemInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lungyu on 3/3/18.
 */

public class LoadAlertCondition extends WebAPIBase{
    private static final String TAG = "LoadAlertCondition";

    private List<ConditionModel> datas = null;

    public LoadAlertCondition(Context context){
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
        return this.context.getString(R.string.web_api_url_alert_condition_list);
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
        if(datas ==  null)
            datas = new ArrayList<>();

        try {

            JSONArray json_response = new JSONArray(response_message);
            for(int i=0;i<json_response.length();i++){
                JSONObject obj = json_response.getJSONObject(i);
                Log.d(TAG,"object index => " + i);

                ConditionModel item = new ConditionModel();
                item.setId(obj.getString(ConditionModel.ALERT_ID));
                item.setActionId(Integer.parseInt(obj.getString(ConditionModel.ACTION_ID)));
                item.setConditionName(obj.getString(ConditionModel.ALERT_NAME));
                item.setConditionValue(obj.getString(ConditionModel.ALERT_CONDITION_VALUE));
                item.setActionDevice(obj.getInt(ConditionModel.ALERT_ACTION_DEVICE));
                datas.add(item);
            }
//            this.token = json_response.getString("Token");
        } catch (JSONException e) {
//            e.printStackTrace();
        }
    }

    public List<ConditionModel> getDatas(){
        return this.datas;
    }

    public void setDatas(List<ConditionModel> source){
        this.datas = source;
    }
}
