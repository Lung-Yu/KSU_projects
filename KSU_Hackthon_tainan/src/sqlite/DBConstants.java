package sqlite;

import android.provider.BaseColumns;

public interface DBConstants extends BaseColumns {
	// ½d¨ÒÄæ¦ì
	public static final String TABLE_NAME = "friends";
	public static final String NAME = "name";
	public static final String TEL = "tel";
	public static final String EMAIL = "email";
	// Member
	public static final String MEMBER_TABLE_NAME = "Member";
	public static final String USER_NAME = "Name";
	public static final String USER_SEX = "Sex";
	public static final String USER_HEIGHT = "Height";
	public static final String USER_WEIGHT = "Weight";
	public static final String USER_AGE = "Age";
	public static final String USER_TIME_STAMP = "TimeStamp";

	// RUNNING
	
	//Image
	public static final String IMAGE_TABLE_NAME = "Image";
	public static final String IMAGE_ADRRESS = "addrress";
	public static final String IMAGE_TIME_SIAMP = "time_stamp";
	public static final String IMAGE_DIST = "dist";
	public static final String IMAGE_START_TIME = "start_time";
	public static final String IMAGE_CALORIE = "calorie";
	// Tnsport
	public static final String TNSPORT_TABLE_NAME = "tnsport";
	public static final String TNSPORT_LID = "lid";
	public static final String TNSPORT_CID = "cid";
	public static final String TNSPORT_TITLE = "title";
	public static final String TNSPORT_URL = "url";
	public static final String TNSPORT_ADRRESSES = "address";
	public static final String TNSPORT_ZIP = "zip";
	public static final String TNSPORT_CITY = "city";
	public static final String TNSPORT_COUNT = "country";
	public static final String TNSPORT_LON = "lon";
	public static final String TNSPORT_LAT = "lat";
	public static final String TNSPORT_ZOOM = "zoom";
	public static final String TNSPORT_PHONE = "phone";
	public static final String TNSPORT_MOBILE = "mobile";
	public static final String TNSPORT_FAX = "fax";
	public static final String TNSPORT_CONTEMAIL = "contemail";
	public static final String TNSPORT_OPENTIME = "opentime";
	public static final String TNSPORT_LOGOURL = "logourl";
	public static final String TNSPORT_SUBMITTER = "submitter";
	public static final String TNSPORT_STATUS = "status";
	public static final String TNSPORT_DATE = "date";
	public static final String TNSPORT_HITS = "hits";
	public static final String TNSPORT_RATING = "rating";
	public static final String TNSPORT_VOTES = "votes";
	public static final String TNSPORT_COMMENTS = "comments";
}
