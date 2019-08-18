package _util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class localJson {
	public static void readJson(String reslut) {
		try {
			JSONObject level0 = new JSONObject(reslut);
			JSONArray level1 = level0.getJSONArray("-table");
			for (int i = 0; i < level1.length(); i++) {
				JSONArray level2 = level1.getJSONArray(i);
				JSONArray level3 = level2.getJSONArray(1);
				for (int j = 0; j < level3.length(); j++) {
					JSONObject json = level3.getJSONObject(j);
				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String readLocationTxt(InputStream input) {
		// InputStream is = getResources().openRawResource(R.raw.aaa);
		StringBuilder strbuf = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			String line;
			while ((line = reader.readLine()) != null) {
				strbuf.append(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strbuf.toString();
	}

}
