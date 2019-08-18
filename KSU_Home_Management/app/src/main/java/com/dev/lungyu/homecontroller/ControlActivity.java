package com.dev.lungyu.homecontroller;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.GridView;

import com.dev.lungyu.homecontroller.fragment.situation.items.GridAdapter;
import com.dev.lungyu.homecontroller.fragment.situation.items.GridItem;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemSwitch;
import com.dev.lungyu.homecontroller.webapi.LoadControlDevices;
import com.dev.lungyu.homecontroller.webapi.base.WebApiCallBack;
import com.dev.lungyu.homecontroller.webapi.datamodel.ControlDeviceInfo;


import java.util.ArrayList;
import java.util.List;


public class ControlActivity extends Navigation_BaseActivity {

    private static String TAG = "ControlActivity";

    private ViewPager mViewPager;
    private List<Fragment> pageList;

    private Context context = this;

    private GridView gridView;
    private List<GridItem> list = new ArrayList<>();
    private GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        page_init(R.string.navigation_control, PagePosition.CONTROL_PAGE);

        initView();
    }

    private void initView() {
        gridView = (GridView) findViewById(R.id.gridview1);
        adapter = new GridAdapter(context, list);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        list.clear();

        final LoadControlDevices service = new LoadControlDevices(this);
        service.setCallBackFunc(new WebApiCallBack() {
            @Override
            public void onProcessCallBack() {
                System.out.println("load devices call back func.");

                int temptype = -1;
                String tempMac = "";
                List<ControlDeviceInfo> infos = service.getDatas();
                for(int i=0;i<infos.size();i++){
                    ControlDeviceInfo item = infos.get(i);

                    //pass repeat data
                    if(item.getSensorId() == temptype && tempMac.equals(item.getDeviceId()))
                        continue;
                    temptype = item.getSensorId();
                    tempMac = item.getDeviceId();

                    //add data into adapter list.
                    Log.d(TAG,tempMac+ " :: sensor id = " + item.getSensorId());
                    list.add(ItemSwitch.get(
                            context,
                            item.getSensorId(),
                            item.getDeviceId(),
                            item.getDeviceName() + "-" + item.getSensorName(),
                            item.getDataValue()
                    ));
                }
                Log.d(TAG,list.size()+"");
                adapter.notifyDataSetChanged();
            }
        });
        service.execute();
    }
}
