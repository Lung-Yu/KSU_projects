package sqlite;

import static android.provider.BaseColumns._ID;
import static sqlite.DBConstants.*;
import sqlite.DBConstants;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	private final static String DATABASE_NAME = "hackathon.db";
	private final static int DATABASE_VERSION = 1;
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// final String INIT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID
		// + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " CHAR, "
		// + TEL + " CHAR, " + EMAIL + " CHAR);";
		final String CREATE_MEMBER_TABLE = "CREATE TABLE " + MEMBER_TABLE_NAME + "("
				+ _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ USER_NAME	+ " CHAR," 
				+ USER_SEX + " CHAR," 
				+ USER_HEIGHT + " CHAR,"
				+ USER_WEIGHT + " CHAR," 
				+ USER_AGE + " INTEGER,"
				+ USER_TIME_STAMP + " CHAR);";
		final String CREATE_TNSPORT_TABLE = "CREATE TABLE " + TNSPORT_TABLE_NAME + "("
				+ TNSPORT_LID +" int(11),"
				+ TNSPORT_CID + " int(5),"
		        + TNSPORT_TITLE + " varchar(100),"
		        + TNSPORT_URL + " varchar(250),"
		        + TNSPORT_ADRRESSES + " varchar(100),"
		        + TNSPORT_ZIP + " varchar(20),"
		        + TNSPORT_CITY + " varchar(100),"
		        + TNSPORT_COUNT + " varchar(100),"
		        + TNSPORT_LON + " float,"
		        + TNSPORT_LAT + " float,"
		        + TNSPORT_ZOOM + " tinyint(2),"
		        + TNSPORT_PHONE + " varchar(40),"
		        + TNSPORT_MOBILE + " varchar(40),"
		        + TNSPORT_FAX + " varchar(40),"
		        + TNSPORT_CONTEMAIL + " varchar(100),"
		        + TNSPORT_OPENTIME + " varchar(100),"
		        + TNSPORT_LOGOURL + " varchar(150),"
		        + TNSPORT_SUBMITTER + " int(11),"
		        + TNSPORT_STATUS + " tinyint(2),"
		        + TNSPORT_DATE + " int(10),"
		        + TNSPORT_HITS + " int(11),"
		        + TNSPORT_RATING +" double(6,4),"
		        + TNSPORT_VOTES + " int(11),"
		        + TNSPORT_COMMENTS + " int(11));"; 
		final String CREATE_IMAGE_TABLE = "CREATE TABLE " + IMAGE_TABLE_NAME + "("
				+ IMAGE_ADRRESS + " CHAR,"
				+ IMAGE_TIME_SIAMP + " CHAR"
				+ IMAGE_DIST + " CHAR,"
				+ IMAGE_START_TIME +" CHAR,"
				+ IMAGE_CALORIE +" CHAR);";
		
		Log.i("DBHelper_onCreate", CREATE_MEMBER_TABLE);
		final String CREATE_RUNNING_TABLE = "";
		db.execSQL(CREATE_MEMBER_TABLE);
		db.execSQL(CREATE_TNSPORT_TABLE);
		db.execSQL(CREATE_IMAGE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		final String DROP_TABLE_MEMBER = "DROP TABLE IF EXISTS " + MEMBER_TABLE_NAME;
		final String DROP_TABLE_TNSPORT = "DROP TABLE IF EXISTS " + TNSPORT_TABLE_NAME;
		final String DROP_TABLE_IMAGE ="DROP TABLE IF EXISTS "+ IMAGE_TABLE_NAME;
		db.execSQL(DROP_TABLE_MEMBER);
		db.execSQL(DROP_TABLE_TNSPORT);
		db.execSQL(DROP_TABLE_IMAGE);
		onCreate(db);
	}
}
