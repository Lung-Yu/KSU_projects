package com.dev.lungyu.homecontroller.fragment.situation.items;

import android.view.View;

/**
 * Created by lungyu on 2/24/18.
 */

public abstract class GridItem {
    abstract int getViewId();
    abstract void initView(View v);
}
