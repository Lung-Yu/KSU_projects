package com.dev.lungyu.homecontroller.webapi;

import android.content.Context;
import android.util.Log;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.webapi.base.ServiceConnector;
import com.dev.lungyu.homecontroller.webapi.base.WebAPIBase;
import com.dev.lungyu.homecontroller.webapi.datamodel.ConditionModel;

import org.json.JSONObject;

/**
 * Created by lungyu on 3/4/18.
 */

public class SendAlertCondition extends WebAPIBase {

    private static final String TAG = "SendAlertCondition";

    private String in_name;
    private String in_mac;
    private String in_device_type;
    private String in_condition_value;
    private String in_option;

    private String out_mac;
    private String out_type;
    private String out_action_status;

    public SendAlertCondition(Context context){
        super(context);
    }

    public SendAlertCondition(Context context, ConditionModel model){
        super(context);
        this.in_name = model.getConditionName();
        this.in_mac = model.getInputArduinoMac();
        this.in_device_type = String.valueOf(model.getDeviceId());
        this.in_condition_value = String.valueOf(model.getConditionValue());
        this.out_mac = model.getOutputArduinoMac();
        this.in_option =String.valueOf(model.getOptionId());
        this.out_type = String.valueOf(model.getActionDeviceId());
        this.out_action_status = String.valueOf(model.getActionId());
    }


    @Override
    protected JSONObject getParams() {
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }

    @Override
    protected String getUrl() {
        return context.getString(R.string.web_api_url_alert_condition) +
                String.format("?in_name=%s&in_mac=%s&in_device_type=%s&in_condition_value=%s&in_option=%s&out_mac=%s&out_type=%s&action_status=%s",
                        in_name,in_mac,in_device_type,in_condition_value,in_option,
                        out_mac,out_type,out_action_status);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        service_connect();
        parser_response();
        return null;
    }

    private void service_connect(){
        ServiceConnector connector = new ServiceConnector(getUrl());
        Log.d(TAG,"url => " + getUrl());
        connector.connectGetByToken(this.token);
        response_message = connector.getResponse();
    }

    private void parser_response(){
        Log.d(TAG,response_message);
    }

    public String getIn_name() {
        return in_name;
    }

    public void setIn_name(String in_name) {
        this.in_name = in_name;
    }

    public String getIn_mac() {
        return in_mac;
    }

    public void setIn_mac(String in_mac) {
        this.in_mac = in_mac;
    }

    public String getIn_device_type() {
        return in_device_type;
    }

    public void setIn_device_type(String in_device_type) {
        this.in_device_type = in_device_type;
    }

    public String getIn_condition_value() {
        return in_condition_value;
    }

    public void setIn_condition_value(String in_condition_value) {
        this.in_condition_value = in_condition_value;
    }

    public String getIn_option() {
        return in_option;
    }

    public void setIn_option(String in_option) {
        this.in_option = in_option;
    }

    public String getOut_mac() {
        return out_mac;
    }

    public void setOut_mac(String out_mac) {
        this.out_mac = out_mac;
    }

    public String getOut_type() {
        return out_type;
    }

    public void setOut_type(String out_type) {
        this.out_type = out_type;
    }

    public String getOut_action_status() {
        return out_action_status;
    }

    public void setOut_action_status(String out_action_status) {
        this.out_action_status = out_action_status;
    }
}
