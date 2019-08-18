package info;

import android.R.string;
import android.util.Log;

public class rotation_move_jugde {
	private String tag = "rotation_move_jugde";
	private int steering; // 0代表不執行轉動 ,1代表右轉,-1代表左轉
	private int isfirst;
	private int move_flag;
	private boolean east;
	private boolean western;
	private boolean south;
	private boolean north;
	private int _move;

	public rotation_move_jugde() {
		_initData();
	}

	public void touchDown() {
		this._initData();
	}

	public void touchUp() {

	}

	public void move(MoveQuadrante move) {
		Log.i(tag, "moving"+move);
		switch (move) {
		case Zero_degress:
			this.east = true;
			break;
		case Ninety_degress:
			this.north = true;
			break;
		case OneHundredEighty_degress:
			this.western = true;
			break;
		case TwoHundredSeventy_degress:
			this.south = true;
			break;
		}
		_isRotaion();
	}

	public int isRotaion() {
		return this.steering;
	}

	private void _isRotaion() {
		if (this.isfirst == -1) {
			if (this.east)
				this.move_flag = 0;
			if (this.north)
				this.move_flag = 1;
			if (this.western)
				this.move_flag = 2;
			if (this.south)
				this.move_flag = 3;
			this.isfirst = 0;
		} else if (this.isfirst == 0) {
			switch (this.move_flag) {
			case 0:
				if (this.north)
					this._move = -1;
				else
					this._move = 1;
				break;
			case 1:
				if (this.western)
					this._move = -1;
				else
					this._move = 1;
				break;
			case 2:
				if (this.south)
					this._move = -1;
				else
					this._move = 1;
				break;
			case 3:
				if (this.east)
					this._move = -1;
				else
					this._move = 1;
				break;
			}
			this.isfirst = 1;
		} else {
			int count = 0;
			if (this.east)
				count++;
			if (this.western)
				count++;
			if (this.north)
				count++;
			if (this.south)
				count++;
			if (count > 2) {
				_transform();
			}
		}
	}

	private void _initData() {
		this.steering = 0;
		this.isfirst = -1;
		this.east = false;
		this.western = false;
		this.south = false;
		this.north = false;
		this._move = 0;
	}

	private void _transform() {
		Log.i(tag, "transform");
		if (_move > 0)
			steering = 1;
		else if (_move < 0)
			steering = -1;
		else
			steering = 0;
	}
}
