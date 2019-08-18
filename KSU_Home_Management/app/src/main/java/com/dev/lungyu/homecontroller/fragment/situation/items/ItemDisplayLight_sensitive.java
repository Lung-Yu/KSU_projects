package com.dev.lungyu.homecontroller.fragment.situation.items;

import android.view.View;

import com.dev.lungyu.homecontroller.R;
import com.littlejie.circleprogress.CircleProgress;

/**
 * Created by lungyu on 3/3/18.
 */

public class ItemDisplayLight_sensitive extends ItemDisplay{
    private static int MAX_VALUE = 1024;
    public ItemDisplayLight_sensitive(){
        super("Light-sensitive",MAX_VALUE);
    }
    public ItemDisplayLight_sensitive(double val){
        super("Light-sensitive",MAX_VALUE,val);
    }
    public ItemDisplayLight_sensitive(double val,String unit){
        super("Light-sensitive",MAX_VALUE,val,unit);
    }

    @Override
    void initView(View view) {
        super.initView(view);

        CircleProgress progress = (CircleProgress) view.findViewById(R.id.circle_progress_bar2);
        progress.setMaxValue(RANGE_MAX);

        progress.setValue(getValue());
        progress.setUnit(unit);
    }
}