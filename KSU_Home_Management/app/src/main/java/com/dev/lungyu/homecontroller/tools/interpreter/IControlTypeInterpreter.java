package com.dev.lungyu.homecontroller.tools.interpreter;

/**
 * Created by lungyu on 3/6/18.
 */

public interface IControlTypeInterpreter {
    public void setActionId(int actionId);

    public int getActionId();

    public void setActionByName(String name);

    public String getActionName();
}
