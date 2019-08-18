package com.dev.lungyu.homecontroller.webapi.datamodel;

/**
 * Created by lungyu on 3/3/18.
 */

public class ControlDeviceInfo {
    public static final String SENSOR_NAME = "name";
    public static final String DATA = "data";
    public static final String DEVICE_NAME = "device_name";
    public static final String TIME = "time";
    public static final String SENSOR_ID = "type";
    public static final String DEVICE_ID = "mac";
    private String sensorName;
    private String data;
    private String deviceName;
    private String time;
    private String sensorId;
    private String deviceId;

    public double getDataValue(){
        return Double.parseDouble(this.data);
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSensorId() {
        return Integer.parseInt(sensorId);
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
