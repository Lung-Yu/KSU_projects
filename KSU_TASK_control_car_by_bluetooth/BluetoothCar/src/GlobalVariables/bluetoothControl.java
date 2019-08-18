package GlobalVariables;	//全域變數的package
import com.example.bluetoothcar.R;

import android.app.Application;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.view.View;


public class bluetoothControl extends Application{
	public static BluetoothDevice bluetoothDevice = null;
	public static BluetoothSocket bluetoothSocket = null;
}
