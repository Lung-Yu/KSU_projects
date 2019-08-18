package com.dev.lungyu.homecontroller.fragment.situation.items;

import android.view.View;
import android.widget.TextView;

import com.dev.lungyu.homecontroller.R;
import com.littlejie.circleprogress.CircleProgress;

/**
 * Created by lungyu on 2/25/18.
 */

public class ItemDisplayCO2 extends ItemDisplay {
    private static int MAX_VALUE = 10000;
    public ItemDisplayCO2(){
        super("CO2",MAX_VALUE);
    }
    public ItemDisplayCO2(double val){
        super("CO2",MAX_VALUE,val);
    }
    public ItemDisplayCO2(double val,String unit){
        super("CO2",MAX_VALUE,val,unit);
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
