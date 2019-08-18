package com.dev.lungyu.homecontroller.fragment.situation.items;

/**
 * Created by lungyu on 2/25/18.
 */

public abstract class ItemControl extends GridItem {
    protected int currentType;

    public void changeStatus(){
        status_chnage_ui();
        status_change_server();
    }

    protected abstract void status_chnage_ui();
    protected abstract void status_change_server();
}
