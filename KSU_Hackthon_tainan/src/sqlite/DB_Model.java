package sqlite;

import static android.provider.BaseColumns._ID;
import static sqlite.DBConstants.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Queue;

import mod.tainan.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DB_Model {

	private final DBHelper _db;

	public DB_Model(Context context) {
		_db = new DBHelper(context);
	}

	public void close() {
		_db.close();
	}

	public void insertMember(String userName, String sex, String userHeight,
			String userWeight, int userAge) {
		SQLiteDatabase db = _db.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_NAME, userName);
		// String sexStr = "";
		values.put(USER_SEX, sex);
		values.put(USER_HEIGHT, userHeight);
		values.put(USER_WEIGHT, userWeight);
		values.put(USER_AGE, userAge);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		values.put(USER_TIME_STAMP, sdFormat.format(new Date()));
		db.insert(MEMBER_TABLE_NAME, null, values);
		// Log.i("setting user info", sexStr);
	}

	public void insertTnsport(String lid, String cid, String title, String url,
			String addr, String zip, String city, String country, String lon,
			String lat, String zoom, String phone, String mobile, String fax,
			String contemail, String opentime, String logourl,
			String submitter, String status, String date, String hits,
			String rating, String votes, String comments) {
		SQLiteDatabase db = _db.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TNSPORT_LID, lid);
		values.put(TNSPORT_CID, cid);
		values.put(TNSPORT_TITLE, title);
		values.put(TNSPORT_URL, url);
		values.put(TNSPORT_ADRRESSES, addr);
		values.put(TNSPORT_ZIP, zip);
		values.put(TNSPORT_CITY, city);
		values.put(TNSPORT_COUNT, country);
		values.put(TNSPORT_LON, lon);
		values.put(TNSPORT_LAT, lat);
		values.put(TNSPORT_ZOOM, zoom);
		values.put(TNSPORT_PHONE, phone);
		values.put(TNSPORT_MOBILE, mobile);
		values.put(TNSPORT_FAX, fax);
		values.put(TNSPORT_CONTEMAIL, contemail);
		values.put(TNSPORT_OPENTIME, opentime);
		values.put(TNSPORT_LOGOURL, logourl);
		values.put(TNSPORT_SUBMITTER, submitter);
		values.put(TNSPORT_STATUS, status);
		values.put(TNSPORT_DATE, date);
		values.put(TNSPORT_HITS, hits);
		values.put(TNSPORT_RATING, rating);
		values.put(TNSPORT_VOTES, votes);
		values.put(TNSPORT_COMMENTS, comments);
		db.insert(TNSPORT_TABLE_NAME, null, values);
	}

	public String insertImage(String startTime, String addr,double dist,double calorie) {
		SQLiteDatabase db = _db.getWritableDatabase();
		ContentValues values = new ContentValues();

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String now = sdFormat.format(new Date());
		values.put(IMAGE_ADRRESS, addr + now);
		values.put(IMAGE_START_TIME, startTime);
		values.put(IMAGE_TIME_SIAMP, now);
		values.put(IMAGE_DIST, dist);
		values.put(IMAGE_CALORIE, calorie);
		db.insert(IMAGE_TABLE_NAME, null, values);
		return now;
	}

	public void updateMember(String userName, String sex, String userHeight,
			String userWeight, int userAge) {
		int _id = 1;
		SQLiteDatabase db = _db.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_NAME, userName);
		String sexStr = "";
		values.put(USER_SEX, sex);
		values.put(USER_HEIGHT, userHeight);
		values.put(USER_WEIGHT, userWeight);
		values.put(USER_AGE, userAge);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		values.put(USER_TIME_STAMP, sdFormat.format(new Date()));
		db.update(MEMBER_TABLE_NAME, values, _ID + "=" + _id, null);
		Log.i("setting user info", sexStr);
	}

	public Cursor getCursor() {

		SQLiteDatabase db = _db.getWritableDatabase();
		String[] columns = { _ID, USER_NAME, USER_SEX, USER_HEIGHT,
				USER_WEIGHT, USER_AGE };
		Cursor cursor = db.query(MEMBER_TABLE_NAME, columns, null, null, null,
				null, null);
		return cursor;
	}
	
	public void None_insert_1(){
//		SQLiteDatabase db = _db.getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put(TNSPORT_LON, 120.221);
//		values.put(TNSPORT_LAT, 23.0014);
//		values.put(TNSPORT_TITLE, "成大醫院");
//		db.insert(TNSPORT_TABLE_NAME, null, values);
//		db.close();
//		String INSERT_TNSPORT_TABLE = "INSERT INTO "+TNSPORT_TABLE_NAME
//				+"("+ TNSPORT_LON + ","+TNSPORT_LAT+","+TNSPORT_TITLE+")VALUES"
//				+ "(`120.218`,`23.0167`,`小康社區居家護理體驗營`),"
//				+ "(`120.218`,`23.0167`,`小康社區健康講座、體認活動`),"
//				+ "(`120.218`,`23.0168`,`小康社區太極拳班`),"
//				+ "(`120.212`,`23.0015`,`聚光隊`),"
//				+ "(`120.212`,`23.0018`,`中山土風舞隊`),"
//				+ "(`120.212`,`23.0021`,`南光羽球隊`);";
	}

}
