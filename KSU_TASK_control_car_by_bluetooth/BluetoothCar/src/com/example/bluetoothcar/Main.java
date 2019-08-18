package com.example.bluetoothcar;

import javax.security.auth.Subject;

import GlobalVariables.bluetoothControl;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class Main extends Activity {

	private Window window;
	private LocalActivityManager lam;
	private TabHost _tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.window = this.getWindow();
		this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initTabHost(savedInstanceState);
		new bluetoothControl();
	}

	private void initTabHost(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		lam = new LocalActivityManager(Main.this, false);
		lam.dispatchCreate(savedInstanceState);

		_tabHost = (TabHost) findViewById(R.id.tabHost);
		_tabHost.setup(lam);
		// _tabHost.setup();
		// 鏈結畫面
		// FrameLayout fTab1 = (FrameLayout) findViewById(R.id.tab1);
		// View view1 =
		// LayoutInflater.from(this).inflate(R.layout.link_bluetooth,
		// null);
		// fTab1.addView(view1);
		//
		// FrameLayout fTab2 = (FrameLayout) findViewById(R.id.tab2);
		// View view2 = LayoutInflater.from(this).inflate(R.layout.control_car,
		// null);
		// fTab2.addView(view2);

		// 新增 tab
		this.addTab(_tabHost, "0", "blueToothLink", getResources()
				.getDrawable(R.drawable.bluetooth), new Intent(Main.this,
				BlueTooth.class));

		this.addTab(_tabHost, "1", "controlCar",
				getResources().getDrawable(R.drawable.ic_launcher), new Intent(
						Main.this, controlCar.class));
		_tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				switch (Integer.parseInt(tabId)) {
				case 0:
					break;
				case 1:
					break;
				default:
					break;
				}
			}
		});
		_tabHost.setCurrentTab(0);
	}

	private void addTab(TabHost tab, String tabName, String tabTag,
		Drawable icon, Intent intent) {
		TabSpec tabSpec = tab.newTabSpec(tabName);
		tabSpec.setIndicator(tabTag, icon);
		tabSpec.setContent(intent);
		tab.addTab(tabSpec);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		lam.dispatchPause(isFinishing());
	}

	@Override
	protected void onResume() {
		super.onResume();
		lam.dispatchResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		if(savedInstanceState==null) savedInstanceState = new Bundle();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("Main","onActivityResult");
		
	}
	
	public void onTabActivityResult(int requestCode, int resultCode, Intent data){
	}	
}
