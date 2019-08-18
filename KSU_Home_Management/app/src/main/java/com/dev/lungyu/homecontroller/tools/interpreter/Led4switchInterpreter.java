package com.dev.lungyu.homecontroller.tools.interpreter;

/**
 * Created by lungyu on 3/6/18.
 */

public class Led4switchInterpreter implements IControlTypeInterpreter {
    private int actionId;

    public static String TAG_LED_OFF = "OFF";
    public static String TAG_LED_TYPE_1 = "RED";
    public static String TAG_LED_TYPE_2 = "GREEN";
    public static String TAG_LED_TYPE_3 = "BLUE";

    public Led4switchInterpreter(){

    }

    public Led4switchInterpreter(int actionId){
        this.actionId = actionId;
    }

    public Led4switchInterpreter(String name){
        this.setActionByName(name);
    }

    @Override
    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    @Override
    public int getActionId() {
        return actionId;
    }

    @Override
    public void setActionByName(String name) {
        if(name.equals(TAG_LED_OFF))
            this.actionId = 0;
        else if (name.equals(TAG_LED_TYPE_1))
            this.actionId = 1;
        else if (name.equals(TAG_LED_TYPE_2))
            this.actionId = 2;
        else if (name.equals(TAG_LED_TYPE_3))
            this.actionId = 3;
        else
            this.actionId = -1;
    }

    @Override
    public String getActionName() {
        switch (actionId)
        {
            case 0:return TAG_LED_OFF;
            case 1:return TAG_LED_TYPE_1;
            case 2:return TAG_LED_TYPE_2;
            case 3:return TAG_LED_TYPE_3;
            default:return TAG_LED_OFF;
        }
    }
}
