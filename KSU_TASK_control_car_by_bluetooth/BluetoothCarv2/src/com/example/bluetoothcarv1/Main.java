package com.example.bluetoothcarv1;

import info.MoveQuadrante;
import info._info;
import info.radiusView;
import info.rotation_move_jugde;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

@TargetApi(Build.VERSION_CODES.ECLAIR)
public class Main extends Activity implements _info {

	private Context context = this;
	private static int pageIndex = 0;
	private static String tag = tagMsg[pageIndex];
	private Spinner spBluetoothDevices;
	private Button btnSearch;

	private final BluetoothAdapter _BTadapter = BluetoothAdapter
			.getDefaultAdapter();

	private ArrayList<Map<String, Object>> sp_array = new ArrayList<Map<String, Object>>();
	private ProgressDialog progreeDialog;
	private BTctrl _BTctrl;

	private int radiusX;
	private int radiusY;
	private int radiuslength;
	private int radiusDif;

	private rotation_move_jugde isRotation = new rotation_move_jugde();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 設定螢幕為垂直
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		Log.d(tag, "init View");
		switch (pageIndex) {
		case 0:
			setContentView(currentLayout[pageIndex]);
			spBluetoothDevices = (Spinner) findViewById(R.id.bluedrives);
			btnSearch = (Button) findViewById(R.id.btnSearch);
			initBluetoothDrives();
			btnSearch.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.d(tag, "btn search on click listener");
					final BluetoothDevice BTD = (BluetoothDevice) sp_array.get(
							spBluetoothDevices.getSelectedItemPosition()).get(
							"device");
					progreeDialog = ProgressDialog.show(Main.this, "藍芽連線",
							BTD.getName() + "連線中");
					_BTctrl = new BTctrl(BTD);
					progreeDialog.dismiss();
					pageIndex = 1;
					initView();
				}
			});
			break;
		case 1:
			RelativeLayout layout = new RelativeLayout(context);

			radiusView radius = new radiusView(context) {
				@Override
				protected void onDraw(Canvas canvas) {
					super.onDraw(canvas);
					drawRadius(canvas);
					radiuslength = this.radius;
					radiusX = this.radius_center_x;
					radiusY = this.radius_center_y;
				}
			};

			ImageButton btnStop = new ImageButton(context);
			btnStop.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));
			RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
			btnStop.setImageResource(R.drawable.ic_launcher);
			btnStop.setOnClickListener(btnCtrlL);

			layout.addView(radius);
			layout.addView(btnStop, rlp);
			setContentView(layout);
		default:
			break;
		}
	}

	private android.widget.Button.OnClickListener btnCtrlL = new android.widget.Button.OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			// _BTctrl.flush(c_goForWard);
			byte[] b = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			_BTctrl.flush(b);
		}
	};

	private void initBluetoothDrives() {
		// TODO Auto-generated method stub
		initBT();
		ArrayList<String> array = new ArrayList<String>();
		Set<BluetoothDevice> pairedDevices = _BTadapter.getBondedDevices();
		Log.i(tag, "devices count = " + pairedDevices.size());
		for (BluetoothDevice device : pairedDevices) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", device.getName());
			map.put("address", device.getAddress());
			map.put("device", device);
			sp_array.add(map);
			Log.i(tag,
					String.format("Name:%s\nMac=%s", device.getName(),
							device.getAddress()));
			array.add(device.getName());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, array);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spBluetoothDevices.setAdapter(adapter);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean rt = super.onTouchEvent(event);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		double touchX = event.getX();
		double touchY = event.getY();
		final double dif = 0.8;

		// X = x0 - x1 , Y = y0 - y1 , r = r
		int x = (int) (radiusX - touchX) * -1;
		int y = (int) (radiusY - touchY + 25); // y0-y1 + ( high mistack)
		int r = (int) radiuslength;
		// x square + y square :: r square
		if (!((Math.pow(x, 2) + Math.pow(y, 2)) > Math.pow(r, 2))) { // 圓內或圓上

			// rotation_move_flag
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				isRotation.touchDown();
				break;
			case MotionEvent.ACTION_MOVE:
				if (x == 0 && y == 0) {
					isRotation.move(MoveQuadrante.None);
				} else if (x > 0 && y == 0) { // I
					isRotation.move(MoveQuadrante.Zero_degress);
				} else if (x == 0 && y > 0) { // II
					isRotation.move(MoveQuadrante.Ninety_degress);
				} else if (x < 0 && y == 0) { // III
					isRotation.move(MoveQuadrante.OneHundredEighty_degress);
				} else if (x == 0 && y < 0) { // IV
					isRotation.move(MoveQuadrante.TwoHundredSeventy_degress);
				} else {
				}
				break;
			case MotionEvent.ACTION_UP:
				isRotation.touchUp();
				break;
			}

			byte[] b = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			b[0] = (byte) (((x > 0) ? 1 : (x < 0) ? 2 : 0) & 0xff);
			b[1] = (byte) (((int) (Math.abs(x * dif)) & 0xff));
			b[2] = (byte) (((y > 0) ? 1 : (y < 0) ? 2 : 0) & 0xff);
			b[3] = (byte) (((int) (Math.abs(y * dif)) & 0xff));
			if (isRotation.isRotaion() > 0) {
				b[4] = (byte) 1; // 自轉順時針
			} else if (isRotation.isRotaion() < 0) {
				b[4] = (byte) 2; // 自轉逆時針
			} else {
				b[4] = (byte) 0;
			}
			_BTctrl.flush(bytes2CharString(b).getBytes());
		} else {
		}

		return rt;
	}

	private static String bytes2CharString(byte[] bs) {
		// TODO Auto-generated method stub
		String str = "";
		for (byte b : bs) {
			str += (char) b;
		}
		return str;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(tag, "event count" + event.getRepeatCount());
		if (KeyEvent.KEYCODE_BACK == keyCode && event.getRepeatCount() == 0) {
			Log.d(tag, "page Index = " + pageIndex);
			switch (pageIndex) {
			case 0:
				this.finish();
				break;
			case 1:
				_BTctrl.free();
				break;
			default:
				break;
			}
		}
		return super.onKeyDown(keyCode, event);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.QuitApp:
			// if(pageIndex!=0)
			// _BTctrl.free();
			this.finish();

			break;
		default:
			break;
		}
		return true;
	}

	private void initBT() {
		if (_BTadapter != null) { // 判斷是否有資源藍芽功能
			if (!_BTadapter.isEnabled()) { // 判斷是否啟動藍芽
				Intent BTEnabled = new Intent(_BTadapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(BTEnabled, REQUEST_CODE);
				Log.i(tag, "ask open bluetooth");
			} else {
				Log.i(tag, "bluetooth is open");
			}
		} else {
			Log.e(tag, "Device does not support Bluetooth");
		}
	}

	private static class BTctrl implements _info {
		private BluetoothSocket _BTSocket;
		private InputStream _input;
		private OutputStream _output;

		public BTctrl(BluetoothDevice btDevice) {
			try {
				_BTSocket = btDevice.createRfcommSocketToServiceRecord(_uuid);
				_BTSocket.connect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			initIO();
			this.flush(c_stop);
		}

		private void initIO() {
			try {
				_input = _BTSocket.getInputStream();
				_output = _BTSocket.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// public static int byteArray2int(byte[] b) {
		// byte[] a = new byte[4];
		// int i = a.length - 1, j = b.length - 1;
		// for (; i >= 0; i--, j--) {// 从b的尾部(即int值的低位)开始copy数据
		// if (j >= 0)
		// a[i] = b[j];
		// else
		// a[i] = 0;// 如果b.length不足4,则将高位补0
		// }
		// int v0 = (a[0] & 0xff) << 24;//
		// &0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
		// int v1 = (a[1] & 0xff) << 16;
		// int v2 = (a[2] & 0xff) << 8;
		// int v3 = (a[3] & 0xff);
		// return v0 + v1 + v2 + v3;
		// }

		private void free() {
			try {
				_input.close();
				_output.close();
				_BTSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void flush(byte[] msg) {
			try {
				// Log.d(tag, decodeInfo(msg));
				_output.write(msg);
				_output.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//
		// public void flush(int msg) {
		// try {
		// // Log.d(tag, decodeInfo(msg));
		// _output.write(int2byteArray(msg));
		// _output.flush();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		// private byte[] int2byteArray(int num) {
		// byte[] result = new byte[4];
		// result[0] = (byte) (num >>> 24);// 取最高8位放到0下标
		// result[1] = (byte) (num >>> 16);// 取次高8为放到1下标
		// result[2] = (byte) (num >>> 8); // 取次低8位放到2下标
		// result[3] = (byte) (num); // 取最低8位放到3下标
		// return result;
		// }

		// private String decodeInfo(byte[] data) {
		// if (c_back.equals(data))
		// return "c_back";
		// else if (c_goForWard.equals(data))
		// return "c_goForWard";
		// else if (c_left.equals(data))
		// return "c_left";
		// else if (c_leftBack.equals(data))
		// return "c_leftBack";
		// else if (c_leftFront.equals(data))
		// return "c_leftFront";
		// else if (c_right.equals(data))
		// return "c_right";
		// else if (c_rightFront.equals(data))
		// return "c_rightFront";
		// else if (c_rightBack.equals(data))
		// return "c_rightBack";
		// else if (c_stop.equals(data))
		// return "c_stop";
		// else if (c_rightRotation.equals(data))
		// return "c_rightRotation";
		// else if (c_leftRotation.equals(data))
		// return "c_leftRotation";
		// else
		// return byteArray2int(data) + "";
		// }
	}
}
