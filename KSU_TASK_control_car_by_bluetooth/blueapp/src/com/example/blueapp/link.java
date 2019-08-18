package com.example.blueapp;
import android.app.Activity;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.view.Menu;
import  android.widget.ImageView;
import  android.widget.CompoundButton.OnCheckedChangeListener;
public class link extends Activity {
	private static ScrollView scrollView;
    private TextView msgText, sendEdit;
    //static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    static final UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    static final String tag = "BtSPP";
   
    //ToggleButton btSwitch;
    ArrayList<String> devices = new ArrayList<String>();
    ArrayAdapter<String> devAdapter, adapter1;
    BluetoothAdapter btAdapt;
    BluetoothSocket btSocket;
    InputStream btIn = null;
    OutputStream btOut;
    SppServer sppServer;
    boolean sppConnected = false;
    Button myButton0, myButton1,lightoff,lighton,myButton6;
    String devAddr=null;
    Spinner spinner1;
    private String msg = "";
    public  static  boolean  ischecked= false ;
    ToggleButton btb;
    ImageView lightonoff;

    /** Called when the activity is first created.*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link);
        
        /*程式開始*/
        scrollView = (ScrollView) this.findViewById(R.id.uart_scrollview);
        msgText = (TextView) findViewById(R.id.uart_view);
        //sendEdit = (TextView) findViewById(R.id.uart_edit);

        myButton0 = (Button) findViewById(R.id.link); // CONNECT
        myButton1 = (Button) findViewById(R.id.find); // DISCOVER
        myButton0.setOnClickListener(myButton0_listener);
        myButton1.setOnClickListener(myButton1_listener);
        
        
        spinner1 = (Spinner) findViewById(R.id.spinner);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, devices);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
        	public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {        		
        		devAddr = ((String) devices.get(position)).split("\\|")[1];
        			adapterView.setVisibility(View.VISIBLE);
        			Toast.makeText(link.this,
        					"您選擇藍芽裝置:" + adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG)
        					.show();        		
        	}
        	
        	public void onNothingSelected(AdapterView<?> arg0) {
        		Toast.makeText(link.this, "您沒有選擇任何藍芽裝置", Toast.LENGTH_LONG).show();			
        	}        	
        });

        btAdapt = BluetoothAdapter.getDefaultAdapter();//初始化藍芽
        /*用BroadcastReceiver來取得搜尋結果*/
        btAdapt.enable();
        IntentFilter intent = new IntentFilter();
        intent.addAction(BluetoothDevice.ACTION_FOUND);
        intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(searchDevices, intent); //註冊廣播接收器
        

        /*執行Serial Port Profile(SPP)服務 Thread*/
                
        sppServer = new SppServer();
        sppServer.start();
    }
    
    private BroadcastReceiver searchDevices = new BroadcastReceiver() {
    	public void onReceive(Context context, Intent intent) {
    		String action = intent.getAction();
    		Bundle b = intent.getExtras();
    		Object[] lstName = b.keySet().toArray();

    		/*顯示所有收到的資訊及細節*/
    		for (int i = 0; i < lstName.length; i++) {
    			String keyName = lstName[i].toString();
    			Log.e(keyName, String.valueOf(b.get(keyName)));
    		}
    		BluetoothDevice device = null;
    		/*搜尋設備時，取得設備的MAC位址*/
    		if (BluetoothDevice.ACTION_FOUND.equals(action)) {
    			device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
    			String str = device.getName() + "|" + device.getAddress();
    			if (devices.indexOf(str) == -1) //防止重複增加
    				devices.add(str); 			//獲得設備名稱和MAC位址
    			adapter1.notifyDataSetChanged();
    			btAdapt.cancelDiscovery();
    		} else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)){
    			device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
    			switch (device.getBondState()) {
    				case BluetoothDevice.BOND_BONDING:
    					Log.d(tag, "正在配對......");
    					break;
    				case BluetoothDevice.BOND_BONDED:
    					Log.d(tag, "完成配對......");
    					break;
    				case BluetoothDevice.BOND_NONE:
    					Log.d(tag, "取消配對......");
    				default:
    					break;
    			}
    		}    		
    	}
    };
    
    /*藍芽連接*/
    private Button.OnClickListener myButton0_listener = new Button.OnClickListener() {
    	
    	public void onClick(View v) {
    		 
    		    		
    		msgText.setText(null);
    		    		
    		if (sppConnected || devAddr == null || btIn != null )
    			return;
    		try {
    			btAdapt.cancelDiscovery();
    			btSocket = btAdapt.getRemoteDevice(devAddr).createRfcommSocketToServiceRecord(uuid);
    			btSocket.connect();
    			Log.d(tag, "BT_Socket connect");
    			
    			synchronized (link.this) {
    				if (sppConnected)
    					return;
    				btServerSocket.close();
    				btIn = btSocket.getInputStream();
    				btOut = btSocket.getOutputStream();
    				conected();
    			}
    			Toast.makeText(link.this, "藍芽裝置已開啟:" + devAddr, Toast.LENGTH_LONG).show();
    		} catch (IOException e) {
    			e.printStackTrace();
    			sppConnected = false;
    			try {
    				btSocket.close();    				
    			} catch (IOException e1) {
    				e1.printStackTrace();
    			}
				btSocket = null;
				Toast.makeText(link.this, "連接埠異常", Toast.LENGTH_SHORT).show();
    		}
    		ctrl();
    	}
    	    	
    };
    
    private BluetoothServerSocket btServerSocket;
    private class SppServer extends Thread {
    	public SppServer() {
    		try {
    			btServerSocket = btAdapt.listenUsingRfcommWithServiceRecord("SPP", uuid);    			
    		} catch (IOException e) {
    			e.printStackTrace();
    			btServerSocket = null;
    		}
    	}
    	
    	public void run() {
    		BluetoothSocket bs = null;
    		if (btServerSocket == null){
    			Log.e(tag, "ServerSocket null");
    			return;
    		}
    		try {
    			bs = btServerSocket.accept();
    			synchronized (link.this) {
    				if (sppConnected)
    					return;
    				Log.i(tag, "Devices Name: " + bs.getRemoteDevice().getName());
	    			btIn = bs.getInputStream();
	    			btOut = bs.getOutputStream();
	    			conected();
	    		}
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    		Log.d(tag, "ServerSoket accept failed");
	    	}
	    	Log.i(tag, "End Bluetooth SPP Server");
    	}
    
	    public void cancel() {
	    	if (btServerSocket == null)
	    		return;
	    	try {
	    		btServerSocket.close();
	    	} catch (IOException e){
	    		e.printStackTrace();
	    		Log.e(tag, "close ServerSocket failed");
	    	}
	    }
    }
    
    private void conected() {
        sppConnected = true;
        new SppReceiver(btIn).start();
        spinner1.setClickable(false);
        sppServer = null;
        Log.e(tag, "conected");
    }
    
    private void disconnect() {
        spinner1.setClickable(true);
        sppConnected = false;
        btIn = null;
        btOut = null;
        sppServer = new SppServer();
        sppServer.start();
        Log.e(tag, "disconnect");
    }
    
    private class SppReceiver extends Thread {
        private InputStream input = null;
        
        public SppReceiver(InputStream in) {
            input = in;
            Log.i(tag, "SppReceiver");
        }
        
        //接收SPP訊息....
        public void run() {
            byte[] data = new byte[1024];
            int length = 0;
            if (input == null) {
                Log.d(tag, "InputStream null");
                return;
            }
            while (true) {
            	try {
            		length = input.read(data);
                    Log.i(tag, "SPP receiver");
                    if (length > 0){
                        msg = new String(data, 0, length, "ASCII") + "\n";
                    }
            	} catch (IOException e) {
                    Log.e(tag, "SppReceiver_disconnect");
                    disconnect();      
            	}}}}    
    
  

    /*藍芽搜尋*/
    private Button.OnClickListener myButton1_listener = new Button.OnClickListener() {
        public void onClick(View v){
        	Toast.makeText(link.this, "正在搜尋中，請耐心等候", Toast.LENGTH_SHORT).show();
        	btAdapt.cancelDiscovery(); 
            btAdapt.startDiscovery();
            }};
            public void link() {
				setContentView(R.layout.link);
		        
		        /*程式開始*/
		        scrollView = (ScrollView) this.findViewById(R.id.uart_scrollview);
		        msgText = (TextView) findViewById(R.id.uart_view);
		        //sendEdit = (TextView) findViewById(R.id.uart_edit);

		        myButton0 = (Button) findViewById(R.id.link); // CONNECT
		        myButton1 = (Button) findViewById(R.id.find); // DISCOVER
		        myButton0.setOnClickListener(myButton0_listener);
		        myButton1.setOnClickListener(myButton1_listener);
		      
			}            
			
			
			public void ctrl() {
				
				setContentView(R.layout.ctrl);		
				lighton = (Button) findViewById(R.id.on); // CONNECT
		        lightoff = (Button) findViewById(R.id.off); // CONNECT
		        lighton.setOnClickListener(lighton_listener);
		        lightoff.setOnClickListener(lightoff_listener);
		        myButton6 = (Button) findViewById(R.id.uart_button6); // EXIT
		        myButton6.setOnClickListener(myButton6_listener);
		        btb = (ToggleButton) findViewById(R.id.toggleButton1); // CONNECT
		        btb.setOnCheckedChangeListener(btb_listener);
		        lightonoff = (ImageView) findViewById(R.id.lightonoff);
		        
			}
			
			
	private Button.OnClickListener lighton_listener = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
					String messageB = "A";
			            try {
			            	btOut.write(messageB.toString().getBytes());
			            } catch (IOException e) {
			        		e.printStackTrace();
			        	}
			            Toast.makeText(link.this, "電燈開啟囉~", Toast.LENGTH_SHORT).show();
				}};		
			
	private Button.OnClickListener lightoff_listener = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			String messageB = "B";
	            try {
	            	btOut.write(messageB.toString().getBytes());
	            } catch (IOException e) {
	        		e.printStackTrace();
	        	}
	            Toast.makeText(link.this, "電燈關閉囉~", Toast.LENGTH_SHORT).show();
		}};	
			
		private OnCheckedChangeListener btb_listener = new OnCheckedChangeListener() {
						@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
							if(isChecked){
								String messageB = "A";
						            try {
						            	btOut.write(messageB.toString().getBytes());
						            } catch (IOException e) {
						        		e.printStackTrace();
						        	}
						            Toast.makeText(link.this, "電燈開啟囉", Toast.LENGTH_SHORT).show();
								}else{
									String messageB = "B";
						            try {
						            	btOut.write(messageB.toString().getBytes());
						            } catch (IOException e) {
						        		e.printStackTrace();
						        	}
						            Toast.makeText(link.this, "電燈關閉囉!!!!", Toast.LENGTH_SHORT).show();	
								} 
			lightonoff.setImageResource(isChecked ? R.drawable.lighton:R.drawable.lightoff);
				
			}};			
			
			
			
		      
			
			
		
    
    
   
    
    /*結束按鍵*/
    private Button.OnClickListener myButton6_listener = new Button.OnClickListener() {
    	
    	
    	
    	
    	
    	public void onClick(View v) {
    		btAdapt.disable();
    		finish();
    	}
    };
   
    
    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }
    
    protected void onDestroy() {
        super.onDestroy();
        if (sppServer != null)
        	sppServer.cancel();
        this.unregisterReceiver(searchDevices);
        if (btIn != null) {
            try {
                btSocket.close();
                btServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }}
        android.os.Process.killProcess(android.os.Process.myPid());
    }}
