package com.dev.lungyu.homecontroller.fragment.furniture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.dev.lungyu.homecontroller.R;
import com.dev.lungyu.homecontroller.fragment.base.BasePageFragment;

/**
 * Created by lungyu on 2/23/18.
 */

public class PageControlFan extends BasePageFragment {
    private View view;
    private TextView textView;
    private ToggleButton btntoggle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        this.view = LayoutInflater.from(getActivity()).inflate(R.layout.page_fan, null);
        initComponents();
        initEventListeners();
        return  this.view;
    }

    private void initComponents(){
        btntoggle = (ToggleButton) view.findViewById(R.id.toggleButton1);
        textView = (TextView) view.findViewById(R.id.text);
    }

    private void initEventListeners(){

    }

    @Override
    public void fetchData() {
        textView.setText("Page Fan");
    }
}