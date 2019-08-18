package com.example.blueapp;

import com.example.blueapp.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Blueapp extends Activity {
	Button enter;
	String accountnumber = "123";
	String passnumber = "123";
	EditText account,pass;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blueapp);
		
		
		
		
		Button enter = (Button)findViewById(R.id.enter);
		enter.setOnClickListener(enter_listener);
		
		
	
	
	}	
		


	private Button.OnClickListener enter_listener = new Button.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			EditText account = (EditText)findViewById(R.id.t1);
			EditText pass = (EditText)findViewById(R.id.t2);
			
			
			if(account.getText().toString().equals("123")){
				if(pass.getText().toString().equals("123")){
					Toast.makeText(Blueapp.this,"登入成功~感謝您的登入", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent();
					  intent.setClass(Blueapp.this, link.class);
					  startActivity(intent);
					
				}else{
					Toast.makeText(Blueapp.this,"password faild!", Toast.LENGTH_SHORT).show();
				}
			}else{
				Toast.makeText(Blueapp.this,"userid faild!", Toast.LENGTH_SHORT).show();
			}
			

			/*
			Intent intent = new Intent();
				intent.setClass(Blueapp.this, link.class);
			  startActivity(intent);
			  Blueapp.this.finish();*/
		}};

}
