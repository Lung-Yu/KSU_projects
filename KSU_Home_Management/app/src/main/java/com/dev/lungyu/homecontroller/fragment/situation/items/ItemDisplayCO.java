package com.dev.lungyu.homecontroller.fragment.situation.items;

import android.view.View;

import com.dev.lungyu.homecontroller.R;
import com.littlejie.circleprogress.CircleProgress;

/**
 * Created by lungyu on 2/25/18.
 */

public class ItemDisplayCO extends ItemDisplay {
    private static int MAX_VALUE = 100000;
    public ItemDisplayCO(){
        super("CO",MAX_VALUE);
    }
    public ItemDisplayCO(double val){
        super("CO",MAX_VALUE,val);
    }
    public ItemDisplayCO(double val,String unit){
        super("CO",MAX_VALUE,val,unit);
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
