package BTSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class BTDataSocket extends Thread {
	private final String tag = this.getName();
	private final BluetoothSocket _Socket;
	private final InputStream _input;
	private final OutputStream _output;
	private boolean isRead = true;
	private String _WriteMsg = "";
	private static int count = 0;

	public BTDataSocket(BluetoothSocket socket) {
		count++;
		if (socket != null) {
			InputStream input = null;
			OutputStream output = null;
			try {
				input = socket.getInputStream();
				output = socket.getOutputStream();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this._Socket = socket;
			this._input = input;
			this._output = output;
			Log.i(tag, "count = " + count);
		} else {
			Log.e(tag, "socket is null");
			throw new NullPointerException();
		}
	}

	@Override
	public void run() {
		try {
			Log.i(tag,"running");
			if (!isRead) {
				if (this._output != null) {
					RunWriter(this._WriteMsg);
					Log.i(tag, "write a data");
				} else
					Log.e(tag, "output stream is null");
			} else {
				if (this._input != null) {
					RunReader();
					Log.i(tag, "reader a data");
				} else
					Log.e(tag, "input stream is null");
			}
			free();
		} catch (IOException e) {
			Log.e(tag, e.getMessage());
		}
	}

	private void RunReader() throws IOException {
		int length = -1;
		byte[] buffer = new byte[1024];
		while (isRead) {
			length = this._input.read(buffer);
			if (length > 0) {
				String msg = new String(buffer, 0, length, "ASCII");
				Log.i(tag, "read : " + msg);
			}
		}
	}

	private void RunWriter(String msg) throws IOException {
		_output.write(msg.getBytes());
		_output.flush();
	}

	public void setMessage(String msg) {
		this._WriteMsg = msg;
	}

	public boolean isWrite() {
		return !this.isRead;
	}

	public boolean isRead() {
		return this.isRead;
	}

	public void setWrite() {
		this.isRead = false;
	}

	public void setRead() {
		this.isRead = true;
	}

	public void free() {
		try {
			this._input.close();
			this._output.close();
			this._Socket.close();
			this.count--;
			Log.i(tag, "be free");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
