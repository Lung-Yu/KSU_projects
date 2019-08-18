package com.dev.lungyu.find_rip.position;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * Created by lungyu on 9/2/17.
 */

public class BleManagement {
    private Context context;

    private Handler mHandler;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;
    private static final long SCAN_PERIOD = 10000; //10 seconds

    private BluetoothAdapter.LeScanCallback leScanCallback;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public BleManagement(Context context){
        this.context = context;
        this.mHandler = new Handler();

        initble();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public BleManagement(Context context, BluetoothAdapter.LeScanCallback LeScanCallback){
        this.context = context;
        this.mHandler = new Handler();
        this.leScanCallback =  LeScanCallback;

        initble();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initble(){
        bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
    }

    public boolean IsDeviceSupportBLE(){
        return context.getPackageManager().hasSystemFeature (PackageManager.FEATURE_BLUETOOTH_LE);
    }

    public boolean IsOpenBluetooth(){
       return (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled());
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void scanLeDevice(final boolean enable) {
        if (enable) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {mBluetoothAdapter.stopLeScan(leScanCallback);
                }
            }, SCAN_PERIOD);

            mBluetoothAdapter.startLeScan(leScanCallback);
        } else {
            mBluetoothAdapter.stopLeScan(leScanCallback);
        }
    }

    public BluetoothAdapter.LeScanCallback getLeScanCallback() {
        return leScanCallback;
    }

    public void setLeScanCallback(BluetoothAdapter.LeScanCallback leScanCallback) {
        this.leScanCallback = leScanCallback;
    }
}
