package com.dev.lungyu.homecontroller.tools.interpreter;

/**
 * Created by lungyu on 3/6/18.
 */

public class OnOffInterpreter implements IControlTypeInterpreter {

    private static final String OFF = "Off";
    private static final String ON = "On";
    private static final String DEFAULT_VALUE = "";

    private int actionId = 0;

    @Override
    public void setActionId(int actionId){
        this.actionId = actionId;
    }

    @Override
    public int getActionId(){
        return this.actionId;
    }

    @Override
    public void setActionByName(String name) {
        if(name.equals(OFF))
            this.actionId = 0;
        else if(name.equals(ON))
            this.actionId = 1;
        else this.actionId = -1;
    }

    @Override
    public String getActionName(){
        switch (actionId){
            case 0:
                return OFF;
            case 1:
                return ON;
            default:
                return DEFAULT_VALUE;
        }
    }
}
