package com.dev.lungyu.find_rip.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

/**
 * Created by lungyu on 9/5/17.
 */

public class MapView extends View {

    private String tag = "radiusView";
    private Paint paint_map;
    private Paint paint_;
    private Paint paint;

    private int col = 20;
    private int row = 10;

    private int x_=5;
    private int y_=7;

    private int x=7;
    private int y=8;

    private float unit_col=0;
    private float unit_row=0;

    private Canvas canvas;

    public MapView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initPaint();
    }

    public MapView(Context context,int row_n,int col_n) {
        super(context);
        // TODO Auto-generated constructor stub
        initPaint();
        this.row = row_n;
        this.col = col_n;
    }
    public MapView(Context context,int row_n,int col_n,int target_x,int target_y) {
        super(context);
        // TODO Auto-generated constructor stub
        initPaint();
        this.row = row_n;
        this.col = col_n;
        setTarget(target_x,target_y);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        drawMap();
        drawTarget();
        drawSelf();
    }

    private void drawMap() {
        int width = getWidth();
        int height = getHeight();
        unit_col = width / col;
        unit_row = height / row;
        for(int i=0;i<=col;i++){
            float unit = unit_col*i;
            canvas.drawLine(unit,0,unit, height,paint_map);
        }

        for(int i=0;i<=row;i++){
            float unit = unit_row*i;
            canvas.drawLine(0,unit,width,unit,paint_map);
        }

        // draw a circle
    }
    private static final int RADIUS = 20;
    private void drawTarget(){
        float cx = x_ * unit_col;
        float cy = y_ * unit_row;

        canvas.drawCircle(cx,cy,RADIUS,paint_);
    }
    private void drawSelf(){
        float cx = x * unit_col;
        float cy = y * unit_row;

        canvas.drawCircle(cx,cy,RADIUS,paint);
    }
    private void initPaint(){
        // prepare a paint
        paint_map = new Paint();
        paint_map.setColor(Color.BLACK);
        paint_map.setStrokeWidth(5);
        paint_map.setAntiAlias(true);

        paint_ = new Paint();
        paint_.setColor(Color.RED);
        paint_.setStrokeWidth(20);
        paint_.setAntiAlias(true);

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
    }


    public void setSelfX(int x){
        this.x = x;
    }
    public void setSelfY(int y){
        this.y = y;
    }
    public void setSelf(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void setTarget(int x,int y){
        this.x_ = x;
        this.y_ = y;
    }
}
