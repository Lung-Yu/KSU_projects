package com.example.bluetoothcar;

import java.util.Vector;

import BTSocket.BTDataSocket;
import GlobalVariables.bluetoothControl;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class controlCar extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_car);
		Window window = this.getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		ImageButton btn1 = (ImageButton) findViewById(R.id.button1);
		ImageButton btn2 = (ImageButton) findViewById(R.id.button2);
		ImageButton btn3 = (ImageButton) findViewById(R.id.button3);
		ImageButton btn4 = (ImageButton) findViewById(R.id.button4);
		ImageButton btn5 = (ImageButton) findViewById(R.id.button5);

		btn1.setOnClickListener(forware);
		btn2.setOnClickListener(forware);
		btn3.setOnClickListener(forware);
		btn4.setOnClickListener(forware);
		btn5.setOnClickListener(forware);

	}

	android.widget.ImageButton.OnClickListener forware = new android.widget.ImageButton.OnClickListener() {
		@Override
		public void onClick(View id) {
			// TODO Auto-generated method stub
			
		    BTDataSocket BTDS = new BTDataSocket(bluetoothControl.bluetoothSocket);
		    BTDS.setWrite();
			switch (id.getId()) {
			case R.id.button1:BTDS.setMessage("F"); 
				break;
			case R.id.button2:BTDS.setMessage("R");
				break;
			case R.id.button3:BTDS.setMessage("L");
				break;
			case R.id.button4:BTDS.setMessage("B");
				break;
			case R.id.button5:BTDS.setMessage("S");
			default:
				break;
			}
			BTDS.start();
		}
	};

}
