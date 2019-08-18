package com.dev.lungyu.homecontroller.fragment.situation.items;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.tools.interpreter.Led4switchInterpreter;
import com.dev.lungyu.homecontroller.webapi.LoadDevices;
import com.dev.lungyu.homecontroller.webapi.SendDeviceSensorAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lungyu on 2/25/18.
 */

public class ItemControlLed extends ItemControl {

    private static final String TAG = "ItemControlLed";

    private String name;

    private TextView textView;
    private TextView textView2;
    private Spinner spinner;

    private Context context;
    private int sensorId;
    private String deviceId;

    private boolean IsControl;

    private List<String> optionlist;


    private ItemControlLed(Context context,String name,int currentType){
        this.context = context;
        this.name = name;
        this.currentType = currentType;

        setControlType();
    }


    public ItemControlLed(String name,int  currentType){
        this(null,name,currentType);
    }

    public ItemControlLed(Context context,int sensorId,String deviceId,String name,int currentType){
        this(context,name,currentType);
        this.sensorId = sensorId;
        this.deviceId = deviceId;
    }

    private void setControlType(){
        IsControl = (context==null)? false:true;
    }

    @Override
    int getViewId() {
        return R.layout.item_control_led;
    }

    @Override
    void initView(View v) {
        textView = (TextView) v.findViewById(R.id.textView);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        textView2 = (TextView) v.findViewById(R.id.textView2);

        textView.setText(this.name);

        if(context == null){
            displayTypeSetup();
        }else{
            controlTypeSetup();
        }

    }

    private void controlTypeSetup(){
        textView2.setVisibility(View.GONE);
        spinner.setVisibility(View.VISIBLE);

        initOptionlist();
        spinner.setEnabled(IsControl);
        final ArrayAdapter adapterCtrldevice = new ArrayAdapter(context, android.R.layout.simple_spinner_item, optionlist);
        spinner.setAdapter(adapterCtrldevice);
        spinner.setSelection(currentType);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"obj::id" + currentType + "   position->" + position);
                changeStatus();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void displayTypeSetup(){
        spinner.setVisibility(View.GONE);
        textView2.setVisibility(View.VISIBLE);
        Led4switchInterpreter interpreter = new Led4switchInterpreter(currentType);
        textView2.setText(interpreter.getActionName());
    }

    private void initOptionlist(){
        optionlist = new ArrayList<>();
        optionlist.add(Led4switchInterpreter.TAG_LED_OFF);
        optionlist.add(Led4switchInterpreter.TAG_LED_TYPE_1);
        optionlist.add(Led4switchInterpreter.TAG_LED_TYPE_2);
        optionlist.add(Led4switchInterpreter.TAG_LED_TYPE_3);
    }



    @Override
    protected void status_chnage_ui() {

    }

    @Override
    protected void status_change_server() {
        SendDeviceSensorAction service = new SendDeviceSensorAction(context);
        Led4switchInterpreter interpreter = new Led4switchInterpreter(currentType);
        service.setAction(String.valueOf(interpreter.getActionId()));
        service.setDeviceId(sensorId);
        service.setMacAddress(deviceId);
        service.execute();
    }
}
