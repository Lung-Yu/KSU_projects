package com.dev.lungyu.homecontroller.fragment.situation.items;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.webapi.SendDeviceSensorAction;

/**
 * Created by lungyu on 2/25/18.
 */

public class ItemControlExhaust_fan extends ItemControl {

    private String name;
    private int sensorId;
    private String deviceId;
    private Context context;

    private boolean IsControl = true;

    private TextView textView;
    private ToggleButton btnToggle;

    private boolean IsEnable;
    private ItemControlExhaust_fan(Context context,String name,int isEnable){
        this.context = context;
        this.IsEnable = isEnable > 0 ? true : false;
        this.name = name;

        setControlType();
    }

    public ItemControlExhaust_fan(String name,int isEnable){
        this(null,name,isEnable);
    }

    public ItemControlExhaust_fan(Context context,int sensorId,String deviceId,String name,int isEnable){
        this(context,name,isEnable);
        this.sensorId = sensorId;
        this.deviceId = deviceId;
    }

    private void setControlType(){
        IsControl = (context==null)? false:true;
    }
    @Override
    int getViewId() {
        return R.layout.item_control_fan;
    }

    @Override
    void initView(View v) {
        textView = (TextView) v.findViewById(R.id.textView);
        btnToggle = (ToggleButton) v.findViewById(R.id.toggleButton1);

        textView.setText(this.name);
        btnToggle.setChecked(IsEnable);
        btnToggle.setEnabled(IsControl);

        btnToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("Item Control",isChecked + "");

                changeStatus();
            }
        });

    }

    @Override
    protected void status_chnage_ui() {

    }

    @Override
    protected void status_change_server() {
        SendDeviceSensorAction service = new SendDeviceSensorAction(context);
        service.setAction(btnToggle.isChecked());
        service.setDeviceId(sensorId);
        service.setMacAddress(deviceId);
        service.execute();
    }
}
