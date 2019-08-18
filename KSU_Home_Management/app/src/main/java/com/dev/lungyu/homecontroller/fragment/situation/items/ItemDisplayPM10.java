package com.dev.lungyu.homecontroller.fragment.situation.items;

import android.view.View;

import com.dev.lungyu.homecontroller.R;
import com.littlejie.circleprogress.CircleProgress;

/**
 * Created by lungyu on 2/25/18.
 */

public class ItemDisplayPM10 extends ItemDisplay{
    private static int MAX_VALUE = 5000;
    public ItemDisplayPM10(){
        super("PM 10",MAX_VALUE);
    }
    public ItemDisplayPM10(double val){
        super("PM 10",MAX_VALUE,val);
    }
    public ItemDisplayPM10(double val,String unit){
        super("PM 10",MAX_VALUE,val,unit);
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
