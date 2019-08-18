package mod.tainan;

import static map.MAPConstants.*;
import static sqlite.DBConstants.*;
import static _util.Operation.*;
import static android.provider.BaseColumns._ID;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.android.gms.internal.br;
import com.google.android.gms.internal.ca;
import com.google.android.gms.internal.m;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.*;
import com.google.android.maps.Overlay.Snappable;

import sqlite.DBHelper;
import sqlite.DB_Model;
import _util.Operation;
import _util.localJson;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Running_map extends Activity implements LocationListener {

	private GoogleMap _Map = null;
	private Marker _My_Marker = null;
	private ArrayList<LatLng> _trace;
	private DB_Model _db = null;
	private TextView _txtDist;
	private final String DistStr = "跑步距離:";
	private final String DistUnit = "m";
	private final String CalorieStr = "卡路里";
	private final String CalorieUnit = "";
	private static final String FileName = "_";
	private static final String FilePath = Environment
			.getExternalStorageDirectory().toString();
	private Bitmap bmScreen;
	private TextView _txtCalorie;
	private Drawable image;
	/** GPS */
	private LocationManager locationMgr;
	private String provider;
	private String startTime = "";

	private Integer[] mThumbIds = { R.drawable.bring_1, R.drawable.bring_2,
			R.drawable.bring_3 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_running);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		_Map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		_txtDist = (TextView) findViewById(R.id.txtDist);
		_txtCalorie = (TextView) findViewById(R.id.txtCalorie);
		_txtDist.setText(DistStr + "0" + DistUnit);

		_db = new DB_Model(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		initMap();
	}

	@Override
	protected void onResume() {
		super.onResume();
		initMap();
	}

	private void initMap() {
		LocationManager lms = (LocationManager) getSystemService(LOCATION_SERVICE); // 取得系統定位服務
		Location location = lms
				.getLastKnownLocation(LocationManager.GPS_PROVIDER); // 使用GPS定位座標
		LatLng _Position = null;
		if (location != null) {
			_Position = new LatLng(location.getLatitude(),
					location.getLongitude());
		} else
			_Position = new LatLng(0, 0);

		_Map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		// this Marker
		MarkerOptions markerOpt = new MarkerOptions();
		markerOpt.position(_Position);
		markerOpt.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.map_icon));
		markerOpt.anchor(0.5f, 0.5f);// 設為圖片中心
		_Map.addMarker(markerOpt);
		_Map.moveCamera(CameraUpdateFactory.newLatLngZoom(_Position, ZOOM));
		//
		DBHelper _db = new DBHelper(this);
		SQLiteDatabase db = _db.getReadableDatabase();
		String[] columns = { TNSPORT_LAT, TNSPORT_LON, TNSPORT_TITLE };
		Cursor cursor = db.query(TNSPORT_TABLE_NAME, columns, null, null, null,
				null, null);
		startManagingCursor(cursor);
		MarkerOptions mk;
		while (cursor.moveToNext()) {
			mk = new MarkerOptions();
			mk.position(new LatLng(Double.parseDouble(cursor
					.getString(0)), Double.parseDouble(cursor.getString(1))));
			mk.title(cursor.getString(2));
			_Map.addMarker(mk);
		}

		// ResolveJSON();
	}

	private void whereAmI() {
		// Location location = Location
		Location location = locationMgr.getLastKnownLocation(provider);
		updateWithNewLocation(location);
		locationMgr.addGpsStatusListener(gpsListener);
		locationMgr.requestLocationUpdates(provider, LISTENER_MIN_TIME,
				LISTENER_MIN_DIST, this);
	}

	/**
	 * GPS初始化，取得可用的位置提供器
	 * 
	 * @return
	 */
	private boolean initLocationProvider() {
		locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// 2.選擇使用GPS提供器
		if (locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
			return true;
		}
		return false;
	}

	private void showMeStay(double lat, double lng) {
		if (_My_Marker != null)
			_My_Marker.remove();
		MarkerOptions MKopt = new MarkerOptions();
		MKopt.position(new LatLng(lat, lng));
		MKopt.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_icon));
		_My_Marker = _Map.addMarker(MKopt);
	}

	private GpsStatus.Listener gpsListener = new GpsStatus.Listener() {

		@Override
		public void onGpsStatusChanged(int event) {
			// TODO Auto-generated method stub
			switch (event) {
			case GpsStatus.GPS_EVENT_STARTED:
				break;
			case GpsStatus.GPS_EVENT_STOPPED:
				break;
			case GpsStatus.GPS_EVENT_FIRST_FIX:
				break;
			case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
				break;
			default:
			}
		}

	};

	// 讓攝影機追著"我"跑
	private void cameraFocusOnMe(double lat, double lng) {
		CameraPosition camPosition = new CameraPosition.Builder()
				.target(new LatLng(lat, lng)).zoom(ZOOM).build();
		_Map.animateCamera(CameraUpdateFactory.newCameraPosition(camPosition));
	}

	private void trackeRoute(double lat, double lng) {
		if (_trace == null)
			_trace = new ArrayList<LatLng>();
		_trace.add(new LatLng(lat, lng));
		PolylineOptions polylineOpt = new PolylineOptions();
		for (LatLng latlng : _trace)
			polylineOpt.add(latlng);
		polylineOpt.color(ROUTE_COLOR);
		Polyline polyline = _Map.addPolyline(polylineOpt);
		polyline.setWidth(ROUTE_WIDTH);
	}

	private void updateWithNewLocation(Location location) {
		if (location != null) {
			double lng = location.getLongitude();
			double lat = location.getLatitude();
			float speed = location.getSpeed();
			long sourceTime = location.getTime();
			String time = TimeFormat(sourceTime);

			// me info
			cameraFocusOnMe(lat, lng);
			trackeRoute(lat, lng);
			showMeStay(lat, lng);
			double dist = distComputing(_trace);
			_txtDist.setText(DistStr + dist + DistUnit);
			_txtCalorie.setText(CalorieStr + calorieComputing(dist)
					+ CalorieUnit);
		} else {
			Log.i(MAP_TAG, "No location found.");
			Toast.makeText(Running_map.this, "No location found.", 1000).show();
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		updateWithNewLocation(location);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		switch (status) {
		case LocationProvider.OUT_OF_SERVICE:
			Log.i(MAP_TAG, "Status Change: Out of Service");
			break;
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			Log.i(MAP_TAG, "Status Change: Temporarily Unavailable");
			break;
		case LocationProvider.AVAILABLE:
			Log.i(MAP_TAG, "Status Change: Available");
			break;
		default:
			break;
		}
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		updateWithNewLocation(null);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		updateWithNewLocation(null);
	}

	public void end_runed(View view) {
		// View screen = (View) findViewById(R.id.runningView);
		//
		// screen.setDrawingCacheEnabled(true);
		// bmScreen = screen.getDrawingCache();
		// Screen();
		double dist = distComputing(_trace);
		// _txtDist.setText(DistStr + dist + DistUnit);
		// _txtCalorie.setText(CalorieStr + calorieComputing(dist)
		// + CalorieUnit);
		_db.insertImage(startTime, "captured_Bitmap", dist,
				calorieComputing(dist));
		// saveImage(bmScreen, _db.);

		// AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		// dialog.setTitle("Title"); // 設定dialog 的title顯示內容
		// dialog.setIcon(android.R.drawable.ic_dialog_alert);// 設定dialog 的ICON
		// dialog.setCancelable(true); // 關閉 Android 系統的主要功能鍵(menu,home等...)
		// dialog.setPositiveButton("收到！", new DialogInterface.OnClickListener()
		// {
		// public void onClick(DialogInterface dialog, int which) {
		// startActivity(new Intent(Running_map.this, MainSwitcher.class));
		// finish();
		// }
		// });
		// dialog.show();
		showPic();
	}

	public void start_running(View view) {
		if (initLocationProvider()) {
			whereAmI();
			_trace = new ArrayList<LatLng>();
			SimpleDateFormat sdFormat = new SimpleDateFormat(
					"yyyy/MM/dd hh:mm:ss");
			startTime = sdFormat.format(new Date());
		} else {
			Log.i(MAP_TAG, "service can not use");
			Toast.makeText(Running_map.this, "請開啟定位!", 1000).show();
		}
		Toast.makeText(Running_map.this, "start_running", 500).show();
	}

	private static void saveImage(Bitmap bmScreen2, String mark) {
		File saved_image_file = new File(FilePath + "/" + FileName + mark
				+ ".png");
		if (saved_image_file.exists())
			saved_image_file.delete();
		try {
			FileOutputStream out = new FileOutputStream(saved_image_file);
			bmScreen2.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			Log.i("--", e.getMessage());
		}

		try {
			// 取得外部儲存裝置路徑

			// 開啟檔案
			File file = new File(FilePath, FileName + ".png");
			if (file.exists())
				file.delete();
			// else
			// file.mkdir();
			// file.createNewFile();
			// 開啟檔案串流
			FileOutputStream out = new FileOutputStream(file);
			// 將 Bitmap壓縮成指定格式的圖片並寫入檔案串流
			bmScreen2.compress(Bitmap.CompressFormat.PNG, 50, out);
			// 刷新並關閉檔案串流
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void Screen() {
	// SnapshotReadyCallback _snapShot = new SnapshotReadyCallback() {
	// @Override
	// public void onSnapshotReady(Bitmap snapshot) {
	// // TODO Auto-generated method stub
	// View screen = (View) findViewById(R.id.runningView);
	// screen.setDrawingCacheEnabled(true);
	// Bitmap backBitmap = screen.getDrawingCache();
	// Bitmap bmOverlay = Bitmap.createBitmap(backBitmap.getWidth(),
	// backBitmap.getHeight(), backBitmap.getConfig());
	// Canvas canvas = new Canvas(bmOverlay);
	// canvas.drawBitmap(snapshot, new Matrix(), null);
	// canvas.drawBitmap(backBitmap, 0, 0, null);
	// try {
	// FileOutputStream out = new FileOutputStream(FilePath + "/"
	// + FileName + ".png");
	// bmOverlay.compress(Bitmap.CompressFormat.PNG, 80, out);
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// };
	// _Map.snapshot(_snapShot);
	// }

	private double calorieComputing(double dist) {
		return (dist * 1.4);
	}

	private void showPic() {
		ImageView iv = new ImageView(Running_map.this);
		iv.setImageResource(mThumbIds[(int) (Math.random() * (mThumbIds.length))]);
		new AlertDialog.Builder(Running_map.this)
				.setTitle(getText(R.string.show_bring)).setView(iv)
				.setPositiveButton("OK 沒問題!", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						startActivity(new Intent(Running_map.this,
								MainSwitcher.class));
						finish();
					}
				}).show();
	}

	private void ResolveJSON() {
		// String str = localJson.readLocationTxt(
		// getResources().openRawResource(R.raw.pstest));
		// Log.i("--", str);
		// localJson.readJson(str);
	}

}
