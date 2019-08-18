package com.dev.lungyu.homecontroller;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.dev.lungyu.homecontroller.fragment.base.FurniturePagerAdapter;
import com.dev.lungyu.homecontroller.fragment.situation.SituationPanel;
import com.dev.lungyu.homecontroller.webapi.LoadDeviceInfo;
import com.dev.lungyu.homecontroller.webapi.LoadDevices;
import com.dev.lungyu.homecontroller.webapi.base.WebApiCallBack;
import com.dev.lungyu.homecontroller.webapi.datamodel.DeviceItemInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Navigation_BaseActivity {

    private ViewPager mViewPager;
    private List<Fragment> pageList;
    private FurniturePagerAdapter adapter;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page_init(R.string.navigation_home, PagePosition.HOME_PAGE);
        initData();
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new FurniturePagerAdapter(getSupportFragmentManager(),pageList);
        mViewPager.setAdapter(adapter);
    }

    private void initData() {
        pageList = new ArrayList<>();

        loadDevice();
    }

    private void loadDevice(){
        final LoadDevices service = new LoadDevices(this);
        service.setCallBackFunc(new WebApiCallBack() {
            @Override
            public void onProcessCallBack() {
                System.out.println("load devices call back func.");

                List<DeviceItemInfo> infos = service.getDatas();
                //load_all(infos);

                for(int i=0;i<infos.size();i++){
                    System.out.println("load device information at "+i+".");
                    SituationPanel panel = new SituationPanel();
                    panel.setDeviceInfo(infos.get(i));
                    pageList.add(panel);

                }

                adapter.notifyDataSetChanged();

            }
        });
        service.execute();
    }

    private void load_all(List<DeviceItemInfo> list){
        for(int i=0;i<list.size();i++){

            final LoadDeviceInfo service = new LoadDeviceInfo(this);
            service.setMacAddress(list.get(i).getMac());
            System.out.println("load device information by " + service.getMacAddress() + ".");
            service.setCallBackFunc(new WebApiCallBack() {
                @Override
                public void onProcessCallBack() {
                    System.out.println("load devices call back func.");

                }
            });
            service.execute();

        }
    }


}
