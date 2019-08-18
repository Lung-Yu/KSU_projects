package info;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class radiusView extends View {
	
	private String tag = "radiusView";
	
	protected int radius_center_x;
	protected int radius_center_y;
	protected int width;
	protected int height;
	protected int radius;

	public radiusView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawRadius(canvas);
	}

	protected void drawRadius(Canvas canvas) {
		width = this.getWidth();
		height = this.getHeight();
		radius = (width > height ? height / 2 : width / 2) - 20;
		radius_center_x = width / 2;
		radius_center_y = height / 2;
		Log.i(tag, String.format("width=%d,height=%d,radius=%d", width,height,radius));
		// prepare a paint
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(5);
		paint.setAntiAlias(true);
		// draw a circle
		canvas.drawCircle(radius_center_x, radius_center_y, radius, paint);
	}

	public int getRadius_center_x() {
		return this.radius_center_x;
	}

	public int getRadius_center_y() {
		return this.radius_center_y;
	}
}
