package com.dev.lungyu.homecontroller.fragment.situation.items;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.webapi.datamodel.ConditionModel;

/**
 * Created by lungyu on 3/4/18.
 */

public class ItemConditionRule extends GridItem {


    private ConditionModel model;
    private String title;
    private String subtitle;
    public static interface OnItemDeleteListener {
        public void delete(ConditionModel model);
    }

    private OnItemDeleteListener onItemDeleteListener = new OnItemDeleteListener() {

        @Override
        public void delete(ConditionModel model) {

        }
    };

    public ItemConditionRule(ConditionModel model,OnItemDeleteListener listener){
        this.title  = model.getConditionName();
        this.subtitle = model.getUIMessage();
        this.model = model;

        this.onItemDeleteListener = listener;
    }

    @Override
    int getViewId() {
        return R.layout.item_condition_rule;
    }

    @Override
    void initView(View v) {
        TextView txtTitle = (TextView) v.findViewById(R.id.textView1);
        TextView txtSubtitle = (TextView) v.findViewById(R.id.textView2);
        Button btnDelete = (Button) v.findViewById(R.id.button);

        txtTitle.setText(title);
        txtSubtitle.setText(subtitle);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemDeleteListener.delete(model);
            }
        });
    }

    public ConditionModel getModel(){
        return this.model;
    }
}
