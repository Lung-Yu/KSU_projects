package mod.tainan;

import static android.provider.BaseColumns._ID;
import static sqlite.DBConstants.*;

import org.json.JSONException;

import sqlite.DBHelper;
import sqlite.DB_Model;
import _util.jsonResultToDB;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.Window;
import android.widget.Toast;

public class Splash extends HttpLoadActivity {

	private DBHelper _db = null;
	private int SPLASH_DISPLAY_LENGHT = 1500;
	private final String jsonUrl = "http://odata.tn.edu.tw/tnsport.json";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		(new Handler()).postDelayed(_Runnable, SPLASH_DISPLAY_LENGHT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	private Runnable _Runnable = new Runnable() {
		@Override
		public void run() {
			db_open();
			boolean isEmpty = db_isEmpty();
			db_close();
			if (isEmpty) {
				startActivity(new Intent(Splash.this, SettingUserInfo.class));
//				new HttpAsyncTask() {
//					@Override
//					protected void onPostExecute(String result) {
//
//					}
//				}.execute(jsonUrl);
				
			} else
				startActivity(new Intent(Splash.this, MainSwitcher.class));
			Splash.this.finish();
		}
	};

	private void db_open() {
		_db = new DBHelper(this);
	}

	private void db_close() {
		_db.close();
	}

	private boolean db_isEmpty() {
		SQLiteDatabase db = _db.getReadableDatabase();
		String[] columns = { _ID, USER_NAME, USER_SEX, USER_HEIGHT,
				USER_WEIGHT, USER_AGE };
		Cursor cursor = db.query(MEMBER_TABLE_NAME, columns, null, null, null,
				null, null);
		startManagingCursor(cursor);

		boolean isEmpty = true;

		while (cursor.moveToNext()) {
			isEmpty = false;
		}
		return isEmpty;
	}

}
