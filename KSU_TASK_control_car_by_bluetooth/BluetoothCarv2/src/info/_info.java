package info;

import java.util.UUID;

import com.example.bluetoothcarv1.R;



public interface _info {
	// _onActivityResult
	int REQUEST_CODE = 1;
	//bluetooth uuid
	UUID _uuid = UUID
			.fromString("00001101-0000-1000-8000-00805F9B34FB");
	//tag message
	String[] tagMsg = { "link to bluetooth","control to car"};
	int[] currentLayout = {R.layout.bluetooth_link,R.layout.main};
	//control car 
	byte[] c_goForWard = "F".getBytes();
	byte[] c_back = "B".getBytes();
	byte[] c_left = "L".getBytes();
	byte[] c_right = "R".getBytes();
	byte[] c_rightFront = "X".getBytes();
	byte[] c_leftFront = "Z".getBytes();
	byte[] c_rightBack = "V".getBytes();
	byte[] c_leftBack = "C".getBytes();
	byte[] c_stop = "S".getBytes();
	byte[] c_rightRotation = "K".getBytes();
	byte[] c_leftRotation = "J".getBytes();

}
