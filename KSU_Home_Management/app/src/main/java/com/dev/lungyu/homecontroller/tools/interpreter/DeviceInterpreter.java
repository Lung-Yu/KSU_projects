package com.dev.lungyu.homecontroller.tools.interpreter;

import com.dev.lungyu.homecontroller.fragment.situation.items.ItemControlExhaust_fan;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemControlFan;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemControlLed;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayCO;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayCO2;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayCombustible_gas;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayFume;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayHumidity;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayInfrared;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayLight_sensitive;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayPM10;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayPM25;
import com.dev.lungyu.homecontroller.fragment.situation.items.ItemDisplayTemperature;

/**
 * Created by lungyu on 3/5/18.
 */

public class DeviceInterpreter {

    private static final String TEMPERATURE = "Temperature";
    private static final String HUMIDITY = "Humidity";
    private static final String PM25 = "PM25";
    private static final String PM10 = "PM10";
    private static final String LIGHT_SENSITIVE = "Light_sensitive";
    private static final String INFRARED = "Infrared";
    private static final String CO = "CO";
    private static final String CO2 = "CO2";
    private static final String FUME = "Fume";
    private static final String COMBUSTIBLE_GAS = "Combustible_gas";
    private static final String LED = "Led";
    private static final String FAN = "Fan";
    private static final String EXHAUST_FAN = "Exhaust_fan";

    private int deviceId;

    public int getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(int deviceId){
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        switch (deviceId) {
            case 1:
                return TEMPERATURE;
            case 2:
                return HUMIDITY;
            case 3:
                return PM25;
            case 4:
                return PM10;
            case 5:
                return LIGHT_SENSITIVE;
            case 6:
                return INFRARED;
            case 7:
                return CO;
            case 8:
                return CO2;
            case 9:
                return FUME;
            case 10:
                //可燃氣體
                return COMBUSTIBLE_GAS;
            case 11:
                return LED;
            case 12:
                return FAN;
            case 13:
                return EXHAUST_FAN;
            default:
                return "";
        }
    }

    public void setDeviceByName(String name) {
        if (name.equals(TEMPERATURE))
            this.deviceId = 1;
        else if (name.equals(HUMIDITY))
            this.deviceId = 2;
        else if (name.equals(PM25))
            this.deviceId = 3;
        else if (name.equals(PM10))
            this.deviceId = 4;
        else if (name.equals(LIGHT_SENSITIVE))
            this.deviceId = 5;
        else if (name.equals(INFRARED))
            this.deviceId = 6;
        else if (name.equals(CO))
            this.deviceId = 7;
        else if (name.equals(CO2))
            this.deviceId = 8;
        else if (name.equals(FUME))
            this.deviceId = 9;
        else if (name.equals(COMBUSTIBLE_GAS))
            this.deviceId = 10;
        else if (name.equals(LED))
            this.deviceId = 11;
        else if (name.equals(FAN))
            this.deviceId = 12;
        else if (name.equals(EXHAUST_FAN))
            this.deviceId = 13;
        else this.deviceId = 0;
    }


}
