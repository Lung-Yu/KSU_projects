package com.dev.lungyu.homecontroller.webapi.datamodel;

/**
 * Created by lungyu on 3/3/18.
 */

public class DeviceInfo {

    public static final String ID = "id";
    public static final String MAC = "mac";
    public static final String TYPE = "type";
    public static final String DATA = "data";
    public static final String Time = "time";
    public static final String NAME = "name";
    public static final String UNIT = "unit";

    private String id;
    private String mac;
    private String type = "0";
    private String data = "0";
    private String time;
    private String name;
    private String unit;


    public int getTypeid(){
        return Integer.parseInt(this.type);
    }

    public double getDataValue(){
        return Double.parseDouble(this.data);
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

