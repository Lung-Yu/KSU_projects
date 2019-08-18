package com.dev.lungyu.homecontroller.fragment.situation.items;

import android.view.View;
import android.widget.TextView;

import com.dev.lungyu.homecontroller.R;
import com.littlejie.circleprogress.CircleProgress;

/**
 * Created by lungyu on 2/25/18.
 */

public abstract class ItemDisplay extends GridItem {
    protected final String NAME;

    protected final int RANGE_MAX;
    protected final int RANGE_MIN;

    protected double value;
    protected String unit;

    private static final int DEFAULT_MIN = 0;

    public ItemDisplay(String name,int max,int min){
        this.NAME = name;
        this.RANGE_MAX = max;
        this.RANGE_MIN = min;
    }

    public ItemDisplay(String name,int max){
        this(name,max,DEFAULT_MIN);
    }


    public ItemDisplay(String name,int max,int min,double value){
        this(name,max,min);

        this.value = value;
    }

    public ItemDisplay(String name,int max,double value){
        this(name,max);

        this.value = value;
    }

    public ItemDisplay(String name,int max,int min,double value,String unit){
        this(name,max,min,value);

        this.unit = unit;
    }

    public ItemDisplay(String name,int max,double value,String unit){
        this(name,max,value);

        this.unit = unit;
    }

    @Override
    int getViewId() {
        return R.layout.item_01;
    }

    @Override
    void initView(View v) {
        TextView txt = (TextView) v.findViewById(R.id.textview);
        txt.setText(NAME);
    }

    float make_radom_value(){
        return (float)( Math.random()*(RANGE_MAX - RANGE_MIN) + RANGE_MIN );
    }

    float getValue(){
        return (float) this.value;
    }
}
