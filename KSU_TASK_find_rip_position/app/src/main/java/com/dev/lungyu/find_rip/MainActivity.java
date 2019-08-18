package com.dev.lungyu.find_rip;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.lungyu.find_rip.position.Ancestors;
import com.dev.lungyu.find_rip.position.Beacon;
import com.dev.lungyu.find_rip.position.BeaconFormateNotFoundException;
import com.dev.lungyu.find_rip.position.BleManagement;
import com.dev.lungyu.find_rip.util.Preferences;
import com.dev.lungyu.find_rip.view.MapView;
import com.dev.lungyu.find_rip.view.ParentsPositionDialog;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String Tag = "MainActivity-BLE";

    private Context context = this;


    private  BleManagement bleManagement;

    private static final int REQUEST_ENABLE_BT = 1;
    private Handler mHandler;
    private static final long SCAN_PERIOD = 10000; //10 seconds

    private TextView textView;
    private MapView mapView;
    private Handler uiHandler = new Handler(){

    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bleInit();

        initView();
        position();

    }

    private void initView(){
        FrameLayout layout = (FrameLayout) findViewById(R.id.map_layout);

        Preferences preferences = new Preferences(context);
        int x = preferences.getTargetPositionX();
        int y = preferences.getTargetPositionY();

        mapView= new MapView(this,10,10,x,y);
        layout.addView(mapView);

        textView = (TextView)findViewById(R.id.textView);
    }

    private void position(){
        final Ancestors ancestors = new Ancestors();

        for(Beacon beacon : Beacon.getSamples())
            ancestors.addBeacon(beacon);

        ancestors.translate();
        ancestors.calc();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(String.format("(X=%f,Y=%f)",ancestors.getX(),ancestors.getY()));
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void bleInit(){
        bleManagement = new BleManagement(this,mLeScanCallback);

        if(!bleManagement.IsDeviceSupportBLE()){
            Toast.makeText(this, "硬體不支援", Toast.LENGTH_SHORT).show();
            finish();
        }

        if(!bleManagement.IsOpenBluetooth()){
            Toast.makeText(this, "請開啟藍芽裝置", Toast.LENGTH_SHORT).show();
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, REQUEST_ENABLE_BT);
        } else {
            bleManagement.scanLeDevice(true);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onPause() {
        super.onPause();
        bleManagement.scanLeDevice(false);
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
                @Override
                public void onLeScan(final BluetoothDevice device, final int rssi,
                                     final byte[] scanRecord) {
                    Log.d("TAG", "BLE device : " + device.getName());

                    try{
                        Beacon beacon = new Beacon(scanRecord,device,rssi);

                        String message = "ibeaconName" +
                                "\nMac：" + beacon.getMacAddress()
                                + " \nUUID：" + beacon.getUuid()
                                + "\nMajor：" + beacon.getMajor()
                                + "\nMinor：" + beacon.getMinor()
                                + "\nTxPower：" + beacon.getTxPower()
                                + "\nrssi：" + rssi;

                        Log.d(Tag, message);

                        Log.d(Tag, "distance：" + beacon.distance());

                        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
                    }catch (BeaconFormateNotFoundException e){
                        Toast.makeText(context,"Beacon Formate Error",Toast.LENGTH_SHORT).show();
                    }
                }
            };

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    // Call Back method  to get the Message form other Activity
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                    bleManagement.scanLeDevice(true);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.menu_set_target:
                onSetTargetPosition();
                break;
        }

        return true;
    }

    private void onSetTargetPosition() {
        ParentsPositionDialog dialog = new ParentsPositionDialog(this, new ParentsPositionDialog.OnClickSaveListener() {
            @Override
            public void onClick() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Preferences preferences = new Preferences(context);
                        int x = preferences.getTargetPositionX();
                        int y = preferences.getTargetPositionY();
                        mapView.setTarget(x,y);
                        mapView.refreshDrawableState();
                    }
                });
            }
        });
        dialog.show();

    }
}
