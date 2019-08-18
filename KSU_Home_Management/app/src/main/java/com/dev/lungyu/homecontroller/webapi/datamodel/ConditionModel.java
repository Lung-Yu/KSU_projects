package com.dev.lungyu.homecontroller.webapi.datamodel;

import com.dev.lungyu.homecontroller.tools.interpreter.DeviceInterpreter;
import com.dev.lungyu.homecontroller.tools.interpreter.IControlTypeInterpreter;
import com.dev.lungyu.homecontroller.tools.interpreter.OnOffInterpreter;
import com.dev.lungyu.homecontroller.tools.interpreter.OptionInterpreter;

/**
 * Created by lungyu on 3/3/18.
 */

public class ConditionModel {

    public static final String ALERT_ID = "alert_id";
    public static final String ACTION_ID = "action_id";
    public static final String ALERT_TYPE = "alert_type";
    public static final String ALERT_MAC = "alert_mac";
    public static final String ALERT_NAME = "name";
    public static final String ALERT_OPTION = "option";
    public static final String ALERT_CONDITION_VALUE = "condition_value";
    public static final String ALERT_ACTION_STATUS = "action_status";
    public static final String ALERT_ACTION_DEVICE = "action_type";


    private String id;
    private String conditionName;
    private double conditionValue;
    private String inputArduinoMac;
    private String outputArduinoMac;

    private DeviceInterpreter deviceInterpreter = new DeviceInterpreter();
    private DeviceInterpreter actionDeviceInterpreter = new DeviceInterpreter();
    private OptionInterpreter optionInterpreter = new OptionInterpreter();
    private IControlTypeInterpreter controlTypeInterpreter;

    public ConditionModel(){
        controlTypeInterpreter = new OnOffInterpreter();
    }

    public ConditionModel(IControlTypeInterpreter controlTypeInterpreter){
        this.controlTypeInterpreter = controlTypeInterpreter;
    }

    public String getUIMessage(){
        return String.format("%s %s when %s %s %.2f .",getActionDevice(),getAction(),getDeviceName(),getOption(),conditionValue);
    }

    public void setActionDevice(int actionDeviceId){
        actionDeviceInterpreter.setDeviceId(actionDeviceId);
    }

    public void setActionDevice(String name){
        actionDeviceInterpreter.setDeviceByName(name);
    }

    public String getActionDevice(){
        return actionDeviceInterpreter.getDeviceName();
    }

    public int getActionDeviceId(){
        return actionDeviceInterpreter.getDeviceId();
    }

    public double getConditionValue(){
        return this.conditionValue;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = Double.parseDouble(conditionValue);
    }
    public void setConditionValue(double conditionValue) {
        this.conditionValue = conditionValue;
    }

    public int getDeviceId(){
        return deviceInterpreter.getDeviceId();
    }

    public String getDeviceName(){
        return deviceInterpreter.getDeviceName();
    }

    public void setDevice(int deviceId){
        deviceInterpreter.setDeviceId(deviceId);
    }
    public void setDevice(String name){
        deviceInterpreter.setDeviceByName(name);
    }

    public int getOptionId(){
        return optionInterpreter.getOptionId();
    }

    public void setOption(String opt){
        optionInterpreter.setOption(opt);
    }

    public String getOption(){
        return optionInterpreter.getOption();
    }

    public String getAction(){
        return controlTypeInterpreter.getActionName();
    }

    public int getActionId() {
        return controlTypeInterpreter.getActionId();
    }

    public void setActionId(int actionId) {
        controlTypeInterpreter.setActionId(actionId);
    }

    public String getConditionName() {
        return conditionName;
    }

    public String getOutputArduinoMac() {
        return outputArduinoMac;
    }

    public void setOutputArduinoMac(String outputArduinoMac) {
        this.outputArduinoMac = outputArduinoMac;
    }

    public String getInputArduinoMac() {
        return inputArduinoMac;
    }

    public void setInputArduinoMac(String inputArduinoMac) {
        this.inputArduinoMac = inputArduinoMac;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
