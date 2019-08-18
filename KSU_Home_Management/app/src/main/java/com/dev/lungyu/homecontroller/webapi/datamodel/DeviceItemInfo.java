package com.dev.lungyu.homecontroller.webapi.datamodel;

/**
 * Created by lungyu on 3/3/18.
 */

public class DeviceItemInfo {
    public static final String MAC = "mac";
    public static final String NAME = "name";
    public static final String TIME = "time";

    private String mac;
    private String name;
    private String time;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
