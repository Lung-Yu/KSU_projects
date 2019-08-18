package mod.tainan;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class testMsg extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_img);
		ArrayList<String> array = new ArrayList<String>();
		array.add("成大醫院");
		array.add("北區消防局");
		array.add("北區裕民里韻律班");
		array.add("公園肚皮舞班");
		array.add("創作流行舞班、韻律動動舞班、國際舞班");
		array.add("永祥瑜珈班");
		array.add("華興振興社交舞班");
		array.add("北區延平國中韻律班");
		array.add("北區國興里桌球班");
		array.add("北區安民里韻律班");
		array.add("台南市北區開元國小");
		array.add("立人國小");
		array.add("公園肚皮舞班");
		array.add("市民學苑韻律舞班");
		ListView _list = (ListView) findViewById(R.id.testlistview);
		ArrayAdapter listAdapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, array);
		_list.setAdapter(listAdapter);
		_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
