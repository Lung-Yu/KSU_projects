package com.dev.lungyu.homecontroller.fragment.situation.items;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.lungyu.homecontroller.R;
import com.littlejie.circleprogress.CircleProgress;

/**
 * Created by lungyu on 2/24/18.
 */

public class Item01 extends GridItem {

    private CircleProgress progress;
    private TextView txt;

    private String message;

    public Item01(){

    }

    public Item01(String message){
        this.message = message;
    }


    @Override
    int getViewId() {
        return R.layout.item_01;
    }

    @Override
    void initView(View view) {
        progress = (CircleProgress) view.findViewById(R.id.circle_progress_bar2);
        txt = (TextView) view.findViewById(R.id.textview);

        progress.setMaxValue(200);

        float value =(float) Math.random()*progress.getMaxValue();

        progress.setValue(value);
        txt.setText(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
