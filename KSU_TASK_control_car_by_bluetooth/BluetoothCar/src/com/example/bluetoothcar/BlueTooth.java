package com.example.bluetoothcar;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import BTSocket.BTDataSocket;
import GlobalVariables.bluetoothControl;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class BlueTooth extends Activity {
	private final BluetoothAdapter _BTAdapter = BluetoothAdapter
			.getDefaultAdapter();
	private final UUID _uuid = UUID
			.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private final String spinnerPrompt = "";
	private final String tag = "BlueTooth";
	private final int REQUEST_CODE = 1;

	private ProgressDialog progreeDialog;
	private ArrayList<Map<String, Object>> _MacDrivces = new ArrayList<Map<String, Object>>();
	private BluetoothDevice _currentDrivce;
	private BluetoothDevice _BTDevice;
	private BluetoothSocket _BTSocket;
	private Spinner searchMac;
	private Button btnSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.link_bluetooth);
		Window window = this.getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initBT();
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		searchMac = (Spinner) findViewById(R.id.spinner);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		initSearchMac();
		btnSearch.setOnClickListener(btnSL);
	}

	private android.widget.Button.OnClickListener btnSL = new android.widget.Button.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String driveName = searchMac.getSelectedItem().toString();
			int drivePosition = searchMac.getSelectedItemPosition();
			_currentDrivce = (BluetoothDevice) ((Map) _MacDrivces
					.get(drivePosition)).get("device");
			progreeDialog = ProgressDialog.show(BlueTooth.this, driveName,
					drivePosition + ":" + _currentDrivce.getAddress());
//			bluetoothControl BTC = (bluetoothControl)getApplicationContext();
//			BTC.bluetoothDevice = _currentDrivce;
//			BTC.bluetoothSocket = _BTSocket;
			bluetoothControl.bluetoothDevice = _currentDrivce;
			bluetoothControl.bluetoothSocket = _BTSocket;
			
			new Thread() {

				@Override
				public void run() {
					Log.i(tag, "running");
					try {
						_BTSocket = _currentDrivce
								.createRfcommSocketToServiceRecord(_uuid);
						_BTSocket.connect();
//						startActivity(new Intent(BlueTooth.this,Main.class).putExtra("pageId", 1));
						Log.i(tag, "connect to Bluetooth");
						progreeDialog.dismiss();
						BTDataSocket BTDS = new BTDataSocket(_BTSocket);
					    BTDS.setWrite();
					    BTDS.setMessage("F");
						BTDS.start();
						
						sleep(10000);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.e(tag, e.getMessage());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						free();
					}
				}
				private void free(){
					try {
						_BTSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
			// findBT();
		}
	};

	/***
	 * @author Lung-Yu search history Bluetooth Drivecs
	 */
	private ArrayList<Map<String, Object>> findHistoryBT() {
		ArrayList<Map<String, Object>> Hdevices = new ArrayList<Map<String, Object>>();
		initBT();
		// 搜尋舊有裝置
		Set<BluetoothDevice> pairedDevices = _BTAdapter.getBondedDevices();
		if (pairedDevices.size() > 0) {
			Log.i(tag, "paireDevices = " + pairedDevices.size());
			for (BluetoothDevice device : pairedDevices) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", device.getName());
				map.put("address", device.getAddress());
				map.put("device", device);
				Hdevices.add(map);
				Log.i(tag, String.format("Name:%s\nMac=%s", device.getName(),
						device.getAddress()));
			}
		} else
			Log.i("BlueTooth", "沒有搜尋到舊有裝置");
		return Hdevices;
	}

	/***
	 * @author Lung-Yu find BlueTooth Drivces
	 */
	private void findBT() {
		initBT();
		IntentFilter filter = new IntentFilter();
		filter.addAction(BluetoothDevice.ACTION_FOUND);
		filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
		filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
		filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
		registerReceiver(searchReceiver, filter);
		_BTAdapter.startDiscovery(); // 開始搜尋裝置
	}

	@Override
	protected void onPause() {
		super.onPause();
		if(_BTAdapter!=null && _BTSocket!=null){
		try {
			if (_BTSocket.isConnected())
				_BTSocket.close();
			if (_BTAdapter.isDiscovering())
				_BTAdapter.cancelDiscovery();
		} catch (IOException e) {
			Log.e(tag, "on pause error" + e.getMessage());
		}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQUEST_CODE:
			Log.i(tag, "BlueTooth Be Open");
			break;
		default:
			break;
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState == null)
			savedInstanceState = new Bundle();
	}

	/***
	 * @author Lung-Yu check isSuper Bluetooth and open Bluetooth Drive
	 * */
	private void initBT() {
		if (_BTAdapter != null) { // 判斷是否有資源藍芽功能
			if (!_BTAdapter.isEnabled()) { // 判斷是否啟動藍芽
				Intent BTEnabled = new Intent(_BTAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(BTEnabled, REQUEST_CODE);
				Log.i(tag, "ask open bluetooth");
			} else {
				Log.i(tag, "bluetooth is open");
			}
		} else {
			Log.e(tag, "Device does not support Bluetooth");
		}
	}

	/***
	 * @author Lung-Yu
	 * @spinner 初始化
	 */
	private void initSearchMac() {
		this._MacDrivces = findHistoryBT();
		ArrayList<String> items = new ArrayList<String>();
		for (Map map : _MacDrivces)
			items.add(map.get("name").toString());
		// String[] strs = new String[] { spinnerPrompt };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		searchMac.setAdapter(adapter);
	}

	/***
	 * @author Lung-Yu
	 * @spinner reflash
	 */
	private void initSearchMac(ArrayList<String> array) {
		ArrayList<String> arrays = new ArrayList<String>();
		arrays.addAll(array);
		searchMac.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, arrays));
	}

	/***
	 * @author Lung-Yu searching Bluetooth Devices
	 */
	private final BroadcastReceiver searchReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			Log.i(tag, "searchReceiver" + action);
			if (_BTDevice.ACTION_FOUND.equals(action)) {
				// BluetoothDevice device =
				if (intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
						.equals(_currentDrivce)) {
					String deviceName = _currentDrivce.getName();
					String deviceAddress = _currentDrivce.getAddress();
					Log.i(tag, "device Name" + deviceName);
					Log.i(tag, "device deviceAddress" + deviceAddress);
					_BTDevice = _currentDrivce;
					progreeDialog.dismiss(); // close progree Dialog
					_BTAdapter.cancelDiscovery(); // stop sreach
					// (new BTConnect(_BTDevice)).start();
				}
			} else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
				BluetoothDevice device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				switch (device.getBondState()) {
				case BluetoothDevice.BOND_BONDING:
					Log.d(tag, "正在配對......");
					break;
				case BluetoothDevice.BOND_BONDED:
					Log.d(tag, "完成配對......");
					break;
				case BluetoothDevice.BOND_NONE:
					Log.d(tag, "取消配對......");
				default:
					break;
				}
			} else {
				Log.i(tag, "Nothing to do\n" + action);
			}
		}
	};
}
