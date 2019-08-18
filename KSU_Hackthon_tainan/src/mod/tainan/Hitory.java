package mod.tainan;

import static sqlite.DBConstants.*;
import static android.provider.BaseColumns._ID;
import static sqlite.DBConstants.MEMBER_TABLE_NAME;
import static sqlite.DBConstants.USER_AGE;
import static sqlite.DBConstants.USER_HEIGHT;
import static sqlite.DBConstants.USER_NAME;
import static sqlite.DBConstants.USER_SEX;
import static sqlite.DBConstants.USER_WEIGHT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sqlite.DBHelper;
import sqlite.DB_Model;

import com.google.android.gms.maps.MapFragment;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Hitory extends Activity {

	private ListView _list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_histroy);
		// requestWindowFeature(Window.FEATURE_NO_TITLE)
		_list = (ListView) findViewById(R.id.listshow);
		ArrayAdapter listAdapter = null;

		listAdapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, getHistory());
		_list.setAdapter(listAdapter);
		_list.setOnItemClickListener(listListener);
	}

	private android.widget.ListView.OnItemClickListener listListener = new android.widget.ListView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	private ArrayList getHistory() {
		DBHelper _db = new DBHelper(this);
		SQLiteDatabase db = _db.getReadableDatabase();
		String[] columns = { IMAGE_START_TIME, IMAGE_TIME_SIAMP };
		Cursor cursor = db.query(IMAGE_TABLE_NAME, columns, null, null, null,
				null, null);
		// 設定日期格式
		ArrayList<String> array = new ArrayList<String>();
		array.add("6/21 - 運動時間長度:7分鐘");
		array.add("6/22 - 運動時間長度:3分鐘");
		array.add("6/22 - 運動時間長度:1分鐘");
		array.add("6/22 - 運動時間長度:5分鐘");
		array.add("6/22 - 運動時間長度:3分鐘");array.add("6/22 - 運動時間長度:0分鐘");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			startManagingCursor(cursor);
			cursor.moveToFirst();
			while (cursor.moveToNext()) {
				Date date_start = sdf.parse(cursor.getString(0));
				Date date_end = sdf.parse(cursor.getString(1));
				String str = String.format("%d/%d - 運動時間長度:%d分鐘",
						date_start.getMonth(), date_start.getDay(),
						(date_end.getMinutes() - date_start.getMinutes()));
				array.add(str);
			}
		} catch (ParseException e) {
			Log.e("sa", "get history error");
		}
		return array;
	}
}
