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
		array.add("���j��|");
		array.add("�_�Ϯ�����");
		array.add("�_�ϸΥ������߯Z");
		array.add("����{�ֻR�Z");
		array.add("�Ч@�y��R�Z�B���߰ʰʻR�Z�B��ڻR�Z");
		array.add("�ò����ɯZ");
		array.add("�ؿ���������R�Z");
		array.add("�_�ϩ����ꤤ���߯Z");
		array.add("�_�ϰ꿳����y�Z");
		array.add("�_�Ϧw�������߯Z");
		array.add("�x�n���_�϶}����p");
		array.add("�ߤH��p");
		array.add("����{�ֻR�Z");
		array.add("�����ǭb���߻R�Z");
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
