package _util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import sqlite.DBHelper;
import sqlite.DB_Model;

public class jsonResultToDB {
	private DB_Model _db;
	private final String JSONObject_level_1 = "database";
	private final String JSONObject_level_2 = "table";
	private final String JSONObject_level_3 = "column";
	private final String JSONObject_level_4 = "-name";
	private final String JSONObject_level_5 = "#text";

	private final Context _context;

	public jsonResultToDB(Context context) {
		this._context = context;
	}

	private void db_open() {
		this._db = new DB_Model(this._context);
	}

	private void db_close() {
		this._db.close();
	}

	
	public void Tnsport(String result) throws JSONException {
		JSONArray jsonArray = new JSONArray(result);
		db_open();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = jsonArray.getJSONObject(i);
			for (int j = 0; j < json.length(); j++) {
				String name = json.getString(JSONObject_level_4);
				Log.i("ss", name);
			}
		}
		db_close();
	}
}
