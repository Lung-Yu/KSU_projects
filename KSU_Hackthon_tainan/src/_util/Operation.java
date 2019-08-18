package _util;

import static android.provider.BaseColumns._ID;
import static sqlite.DBConstants.MEMBER_TABLE_NAME;
import static sqlite.DBConstants.USER_AGE;
import static sqlite.DBConstants.USER_HEIGHT;
import static sqlite.DBConstants.USER_NAME;
import static sqlite.DBConstants.USER_SEX;
import static sqlite.DBConstants.USER_WEIGHT;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;

public class Operation {
	private static String _timeFormat = "yyyy-MM-dd HH:mm:ss";
	private static final double EARTH_RADIUS = 6378137.0;

	public static String TimeFormat(long timeInMilliseconds) {
		SimpleDateFormat format = new SimpleDateFormat(_timeFormat);
		return format.format(timeInMilliseconds);
	}

  

	public static double distComputing(ArrayList<LatLng> dists) {
		double _dist = 0;
		for(int i=1;i<dists.size();i++){
			_dist += gps2m(dists.get(i-1).latitude,dists.get(i-1).latitude,dists.get(i).latitude,dists.get(i).latitude);
		}
		return _dist;
	}

	private static double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
		double radLat1 = (lat_a * Math.PI / 180.0);
		double radLat2 = (lat_b * Math.PI / 180.0);
		double a = radLat1 - radLat2;
		double b = (lng_a - lng_b) * Math.PI / 180.0;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
}
