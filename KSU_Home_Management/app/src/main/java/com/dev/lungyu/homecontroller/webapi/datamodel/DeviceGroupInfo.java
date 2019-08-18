package com.dev.lungyu.homecontroller.webapi.datamodel;

/**
 * Created by lungyu on 3/5/18.
 */

public class DeviceGroupInfo {

    public static final String ID = "id";
    public static final String MAC = "mac";
    public static final String TYPE = "type";
    public static final String TIME = "time";

    private String id;
    private String mac;
    private String type;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
