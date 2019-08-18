package mod.tainan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import sqlite.DB_Model;
import _util.jsonResultToDB;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class MainSwitcher extends HttpLoadActivity {

	final String jsonUrl = "http://odata.tn.edu.tw/tnsport.json";

	private ProgressDialog progreeDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_switcher);
		DB_Model DB = new DB_Model(MainSwitcher.this);
		DB.None_insert_1();
	}

	public void goToGamePage_click(View view) {
		startActivity(new Intent(MainSwitcher.this,testMsg.class));
	}

	public void goToRunningPage_click(View view) {
		startActivity(new Intent(MainSwitcher.this, Running_map.class));
	}

	public void goToShowHistoryPage_click(View view) {
		startActivity(new Intent(MainSwitcher.this, Hitory.class));
	}

	public void goResetInfo(View view) {
		startActivity(new Intent(MainSwitcher.this, ResetUserInfo.class));
	}
	public void toAbout(View view){
		startActivity(new Intent(MainSwitcher.this, about_this_team.class));
	}

	public void loadData(View view) {
		if (isConnected()) {
			progreeDialog = ProgressDialog.show(MainSwitcher.this,
					"opdata load", "loading");
			new HttpAsyncTask() {
				@Override
				protected void onPostExecute(String result) {
					progreeDialog.dismiss();
					Toast.makeText(getBaseContext(), "Received!",
							Toast.LENGTH_LONG).show();
					jsonResultToDB jsonModel = new jsonResultToDB(
							MainSwitcher.this);
					try {
						jsonModel.Tnsport(result);
					} catch (JSONException ex) {

					}
				}
			}.execute(jsonUrl);
		} 
	}

}
