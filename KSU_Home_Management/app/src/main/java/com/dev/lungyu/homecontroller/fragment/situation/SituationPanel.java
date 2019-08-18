package com.dev.lungyu.homecontroller.fragment.situation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.fragment.base.BasePageFragment;
import com.dev.lungyu.homecontroller.fragment.situation.items.GridAdapter;
import com.dev.lungyu.homecontroller.fragment.situation.items.GridItem;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemControlExhaust_fan;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemControlFan;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemControlLed;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayHumidity;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayPM10;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayPM25;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayTemperature;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemSwitch;
import com.dev.lungyu.homecontroller.webapi.LoadDeviceInfo;
import com.dev.lungyu.homecontroller.webapi.base.WebApiCallBack;
import com.dev.lungyu.homecontroller.webapi.datamodel.DeviceInfo;
import com.dev.lungyu.homecontroller.webapi.datamodel.DeviceItemInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lungyu on 3/3/18.
 */

public class SituationPanel extends BasePageFragment {
    private View view;

    private DeviceItemInfo deviceItemInfo;

    public void setDeviceInfo(DeviceItemInfo info ){
        this.deviceItemInfo = info;
    }

    private GridView gridView;
    private List<GridItem> list;
    private TextView txtlabel;

    private GridAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        this.view = LayoutInflater.from(getContext()).inflate(R.layout.layout_situation_panel, null);

        initComponents();
        initEventListeners();
        return  this.view;
    }

    private void initComponents(){
        gridView = (GridView) this.view.findViewById(R.id.gridview1);
        txtlabel = (TextView) this.view.findViewById(R.id.textView1);

        txtlabel.setText(deviceItemInfo.getName());


        list = new ArrayList<>();

        adapter = new GridAdapter(getContext(),list);
        gridView.setAdapter(adapter);
    }

    private void initEventListeners(){

    }

    @Override
    public void fetchData() {
        final LoadDeviceInfo service = new LoadDeviceInfo(getContext());
        service.setMacAddress(this.deviceItemInfo.getMac());
        System.out.println("load device information by " + service.getMacAddress() + ".");
        service.setCallBackFunc(new WebApiCallBack() {
            @Override
            public void onProcessCallBack() {
                System.out.println("load devices call back func.");

                List<DeviceInfo> list = service.getDatas();
                update_datas(list);
            }
        });
        service.execute();
    }

    private void update_datas(List<DeviceInfo> source){
        list.clear();

        int tempId = 0;
        for(int i=0;i<source.size();i++){
            DeviceInfo item = source.get(i);
            if(item.getTypeid() == tempId)
                continue;
            else
                tempId = item.getTypeid();

            list.add(ItemSwitch.get(item.getTypeid(),item.getName(),item.getDataValue(),item.getUnit()));
        }

        adapter.notifyDataSetChanged();
    }
}
