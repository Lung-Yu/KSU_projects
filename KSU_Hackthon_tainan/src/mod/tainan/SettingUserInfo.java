package mod.tainan;

import static sqlite.DBConstants.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import sqlite.DBHelper;
import sqlite.DB_Model;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingUserInfo extends Activity {

	// private DBHelper _db = null;
	private DB_Model _db = null;
	private EditText txtName;
	private RadioGroup radioGroupSex;
	private EditText txtHeight;
	private EditText txtWeight;
	private EditText txtAge;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting_user_information);

		txtName = (EditText) findViewById(R.id.editTxextUserName);
		radioGroupSex = (RadioGroup) findViewById(R.id.radioGroupSex);
		txtHeight = (EditText) findViewById(R.id.editTxex_height);
		txtWeight = (EditText) findViewById(R.id.editTxex_weight);
		txtAge = (EditText) findViewById(R.id.editTxex_age);
		_db = new DB_Model(this);

	}

	public void onSaved(View view) {

		// db_open();
		// saveUserInformation();
		// db_close();
		saveUserInformation();
		_db.close();
		startActivity(new Intent(SettingUserInfo.this, MainSwitcher.class));
		this.finish();
	}

	private void db_open() {
		// _db = new DBHelper(this);
	}

	private void db_close() {
		// _db.close();
	}

	private void saveUserInformation() {
		// SQLiteDatabase db = _db.getWritableDatabase();
		// ContentValues values = new ContentValues();
		// values.put(USER_NAME, txtName.getText().toString());
		// String sexStr = "";
		// switch (radioGroupSex.getCheckedRadioButtonId()) {
		// case R.id.setSexMale:
		// sexStr = getResources().getString(R.string.set_user_male);
		// break;
		// case R.id.setSexFemale:
		// sexStr = getResources().getString(R.string.set_user_female);
		// break;
		// default:
		// sexStr = "none";
		// break;
		// }
		// values.put(USER_SEX, sexStr);
		// values.put(USER_HEIGHT, txtHeight.getText().toString());
		// values.put(USER_WEIGHT, txtWeight.getText().toString());
		// values.put(USER_AGE, Integer.parseInt(txtAge.getText().toString()));
		// SimpleDateFormat sdFormat = new
		// SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		// values.put(USER_TIME_STAMP, sdFormat.format(new Date()));
		// db.insert(MEMBER_TABLE_NAME, null, values);
		// Log.i("setting user info", sexStr);
		String sexStr = "";
		switch (radioGroupSex.getCheckedRadioButtonId()) {
		case R.id.setSexMale:
			sexStr = getResources().getString(R.string.set_user_male);
			break;
		case R.id.setSexFemale:
			sexStr = getResources().getString(R.string.set_user_female);
			break;
		default:
			sexStr = "none";
			break;
		}
		
		try{
			_db.insertMember(
					txtName.getText().toString(),
					sexStr,
					txtHeight.getText().toString(),
					txtWeight.getText().toString()
					, Integer.parseInt(txtAge.getText().toString()));
		}catch(Exception e){
			Toast.makeText(SettingUserInfo.this, "請輸入資料", 100).show();
		}
		
	}

}
