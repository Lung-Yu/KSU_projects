package mod.tainan;

import sqlite.DB_Model;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ResetUserInfo extends Activity {
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
		setContentView(R.layout.activity_reset_user_information);

		txtName = (EditText) findViewById(R.id.editTxextUserName);
		radioGroupSex = (RadioGroup) findViewById(R.id.radioGroupSex);
		txtHeight = (EditText) findViewById(R.id.editTxex_height);
		txtWeight = (EditText) findViewById(R.id.editTxex_weight);
		txtAge = (EditText) findViewById(R.id.editTxex_age);
		_db = new DB_Model(this);
		initViewInfo();
	}

	private void initViewInfo() {
		Cursor cursor = _db.getCursor();
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String name = cursor.getString(1);
			txtName.setText(name);
			String sex = cursor.getString(2);
			if (sex.equals(getResources().getString(R.string.set_user_male))) {
				((RadioButton) (findViewById(R.id.setSexMale)))
						.setChecked(true);
			} else if(sex.equals(getResources().getString(R.string.set_user_female))){
				((RadioButton) (findViewById(R.id.setSexFemale)))
						.setChecked(true);
			}else{

			}
			txtHeight.setText(cursor.getString(3));
			txtWeight.setText(cursor.getString(4));
			txtAge.setText(cursor.getString(5));
		}
	}

	public void onSaved(View view) {

		saveUserInformation();
		_db.close();
		startActivity(new Intent(ResetUserInfo.this, MainSwitcher.class));
		this.finish();
	}

	private void saveUserInformation() {

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

		try {
			_db.updateMember(txtName.getText().toString(), sexStr, txtHeight
					.getText().toString(), txtWeight.getText().toString(),
					Integer.parseInt(txtAge.getText().toString()));
		} catch (Exception e) {
			Toast.makeText(ResetUserInfo.this, "請輸入資料", 100).show();
		}
	}
}
