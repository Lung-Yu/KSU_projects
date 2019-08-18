package com.dev.lungyu.homecontroller;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.dev.lungyu.homecontroller.fragment.situation.SituationPanel;
import com.dev.lungyu.homecontroller.fragment.situation.items.GridAdapter;
import com.dev.lungyu.homecontroller.fragment.situation.items.GridItem;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemConditionRule;
import com.dev.lungyu.homecontroller.tools.interpreter.DeviceInterpreter;
import com.dev.lungyu.homecontroller.webapi.DelAlertCondition;
import com.dev.lungyu.homecontroller.webapi.LoadAlertCondition;
import com.dev.lungyu.homecontroller.webapi.LoadDeviceControlGroup;
import com.dev.lungyu.homecontroller.webapi.LoadDeviceGroup;
import com.dev.lungyu.homecontroller.webapi.LoadDevices;
import com.dev.lungyu.homecontroller.webapi.SendAlertCondition;
import com.dev.lungyu.homecontroller.webapi.base.WebApiCallBack;
import com.dev.lungyu.homecontroller.webapi.datamodel.ConditionModel;
import com.dev.lungyu.homecontroller.webapi.datamodel.DeviceGroupInfo;
import com.dev.lungyu.homecontroller.webapi.datamodel.DeviceItemInfo;

import java.util.ArrayList;
import java.util.List;

public class SetupActivity extends Navigation_BaseActivity {

    private static final String TAG = "SetupActivity";

    private Context context = this;

    private Spinner spinnerDevice;
    private Spinner spinnerOption;
    private Spinner spinnerCtrlDevice;
    private Spinner spinnerArduino1;
    private Spinner spinnerArduino2;
    private EditText editConditionName;
    private EditText editConditionValue;
    private ToggleButton toggleButton;

    private ListView listView;
    private GridAdapter adapter;
    private ArrayAdapter adapterArduino;

    private List<String> arduinoString = new ArrayList<>();
    private List<String> option = new ArrayList<>();
    private List<String> devices = new ArrayList<>();
    private List<String> ctrlDevices = new ArrayList<>();
    private List<GridItem> rules = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        page_init(R.string.navigation_setup, PagePosition.SETUP);

        spinnerDevice = (Spinner) findViewById(R.id.spinner1);
        spinnerOption = (Spinner) findViewById(R.id.spinner2);
        spinnerCtrlDevice = (Spinner) findViewById(R.id.spinner3);
        spinnerArduino1 = (Spinner) findViewById(R.id.spinner);
        spinnerArduino2 = (Spinner) findViewById(R.id.spinner4);

        listView = (ListView) findViewById(R.id.listview);

        editConditionName = (EditText) findViewById(R.id.edittext1);
        editConditionValue = (EditText) findViewById(R.id.edittext2);

        toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);

        initData();

        ArrayAdapter adapterOption = new ArrayAdapter(this, android.R.layout.simple_spinner_item, option);
        spinnerOption.setAdapter(adapterOption);
        final ArrayAdapter adapterdevice = new ArrayAdapter(this, android.R.layout.simple_spinner_item, devices);
        spinnerDevice.setAdapter(adapterdevice);
        spinnerDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final ArrayAdapter adapterCtrldevice = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ctrlDevices);
        spinnerCtrlDevice.setAdapter(adapterCtrldevice);
        spinnerCtrlDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapterArduino = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arduinoString);
        spinnerArduino1.setAdapter(adapterArduino);
        spinnerArduino2.setAdapter(adapterArduino);
        spinnerArduino1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mac = infos.get(position).getMac();
                loadNotControlDevice(devices,adapterdevice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerArduino2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mac = infos.get(position).getMac();
                loadControlDevice(ctrlDevices,adapterCtrldevice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter = new GridAdapter(this, rules);
        listView.setAdapter(adapter);

        LoadAlertCondition();
        loadArduinoInfo();
    }

    private List<DeviceItemInfo> infos;
    private void loadArduinoInfo(){
        final LoadDevices service = new LoadDevices(this);
        service.setCallBackFunc(new WebApiCallBack() {
            @Override
            public void onProcessCallBack() {
                infos = service.getDatas();

                for(int i=0;i<infos.size();i++){
                    arduinoString.add(infos.get(i).getName());
                }

                adapterArduino.notifyDataSetChanged();
            }
        });
        service.execute();
    }

    private void LoadAlertCondition(){
        rule_init();

        final LoadAlertCondition service = new LoadAlertCondition(this);
        service.setCallBackFunc(new WebApiCallBack() {
            @Override
            public void onProcessCallBack() {
                for(ConditionModel model : service.getDatas())
                    convert2UI(model);

                adapter.notifyDataSetChanged();
            }
        });
        service.execute();
    }

    private void rule_init(){
        if(rules!= null)
            rules.clear();
        else
            rules = new ArrayList<>();
    }

    private void loadControlDevice(final List<String> target_list,final BaseAdapter target_adapter){
        final LoadDeviceControlGroup service = new LoadDeviceControlGroup(this);
        service.setControl(true);
        service.setCallBackFunc(new WebApiCallBack() {
            @Override
            public void onProcessCallBack() {
                DeviceInterpreter interpreter = new DeviceInterpreter();
                List<DeviceGroupInfo>  infos = service.getDatas();
                for(int i=0;i<infos.size();i++){
                    interpreter.setDeviceId(Integer.parseInt(infos.get(i).getType()));
                    target_list.add(interpreter.getDeviceName());
                }

                target_adapter.notifyDataSetChanged();
            }
        });
        service.execute();
    }

    private void loadNotControlDevice(final List<String> target_list,final BaseAdapter target_adapter){
        final LoadDeviceControlGroup service = new LoadDeviceControlGroup(this);
        service.setControl(false);
        service.setCallBackFunc(new WebApiCallBack() {
            @Override
            public void onProcessCallBack() {
                DeviceInterpreter interpreter = new DeviceInterpreter();
                List<DeviceGroupInfo>  infos = service.getDatas();
                for(int i=0;i<infos.size();i++){
                    interpreter.setDeviceId(Integer.parseInt(infos.get(i).getType()));
                    target_list.add(interpreter.getDeviceName());
                }

                target_adapter.notifyDataSetChanged();
            }
        });
        service.execute();
    }


    private void loadDeviceGroup(String mac,final List<String> target_list,final BaseAdapter target_adapter){

        Log.d(TAG,"load device group.");
        Log.d(TAG,mac);
        Log.d(TAG,target_list.toString());

        target_list.clear();

        final LoadDeviceGroup service = new LoadDeviceGroup(this);
        service.setMacAddress(mac);
        service.setCallBackFunc(new WebApiCallBack() {
            @Override
            public void onProcessCallBack() {
                DeviceInterpreter interpreter = new DeviceInterpreter();
                List<DeviceGroupInfo>  infos = service.getDatas();
                for(int i=0;i<infos.size();i++){
                    interpreter.setDeviceId(Integer.parseInt(infos.get(i).getType()));
                    target_list.add(interpreter.getDeviceName());
                }

                target_adapter.notifyDataSetChanged();
            }
        });
        service.execute();
    }

    public void onAdd(View v) {
        String condition_name = editConditionName.getText().toString();
        String condition_value = editConditionValue.getText().toString();
        String device = spinnerDevice.getSelectedItem().toString();
        String option = spinnerOption.getSelectedItem().toString();
        String triggerDevice = spinnerCtrlDevice.getSelectedItem().toString();

        if(!inputValid())
            return;

        ConditionModel model = new ConditionModel();
        model.setConditionName(condition_name);

        model.setConditionValue(condition_value);
        model.setDevice(device);
        model.setOption(option);
        model.setActionId(toggleButton.isChecked() ? 1 : 0);
        model.setActionDevice(triggerDevice);
        model.setInputArduinoMac(infos.get((int)spinnerArduino1.getSelectedItemId()).getMac());
        model.setOutputArduinoMac(infos.get((int)spinnerArduino2.getSelectedItemId()).getMac());
        model.setId(String.valueOf(rules.size()));
        addAlert2Server(model);
        Log.d(TAG, model.getUIMessage());


        convert2UI(model);
        inputClear();
    }

    private void convert2UI(ConditionModel model){
        rules.add(new ItemConditionRule(model,onItemDeleteListener));
        adapter.notifyDataSetChanged();
    }

    private void addAlert2Server(ConditionModel model){
        final SendAlertCondition service = new SendAlertCondition(this,model);
        service.execute();
    }

    private ItemConditionRule.OnItemDeleteListener onItemDeleteListener = new ItemConditionRule.OnItemDeleteListener() {
        @Override
        public void delete(ConditionModel model) {
            final DelAlertCondition service = new DelAlertCondition(context);
            service.setAlert_id(String.valueOf(model.getActionId()));
            service.setCallBackFunc(new WebApiCallBack() {
                @Override
                public void onProcessCallBack() {
                    //reload alert list
                    LoadAlertCondition();
                }
            });
            service.execute();
        }
    };

    private void inputClear(){
        editConditionName.setText("");
        editConditionValue.setText("");
    }

    private boolean inputValid() {
        boolean isValid = true;
        if (editConditionName.getText().toString().isEmpty()) {
            editConditionName.setError("空白");
            isValid = false;
        }

        if (editConditionValue.getText().toString().isEmpty()) {
            editConditionValue.setError("空白");
            isValid = false;
        }

        return isValid;
    }

    private void initData() {
        option.add(">");
        option.add("<");
        option.add("=");
        option.add(">=");
        option.add("<=");
    }


}