package com.dev.lungyu.homecontroller.fragment.situation.items;

import android.content.Context;

/**
 * Created by lungyu on 3/3/18.
 */

public class ItemSwitch {
    public static ItemControl get(Context context,int index, String device_id, String name, double value){
        switch (index){
            case 11:
                return new ItemControlLed(context,index,device_id,name,(int)(value));
            case 12:
                return new ItemControlFan(context,index,device_id,name,(int)(value));
            case 13:
                return new ItemControlExhaust_fan(context,index,device_id,name,(int)(value));
            default:
                return null;
        }
    }

    public static ItemDisplay get(int index,double value,String unit){
        switch (index){
            case 1:
                //溫度
                return new ItemDisplayTemperature(value,unit);
            case 2:
                //濕度
                return new ItemDisplayHumidity(value,unit);
            case 3:
                return new ItemDisplayPM25(value,unit);
            case 4:
                return new ItemDisplayPM10(value,unit);
            case 5:
                //光感應
                return new ItemDisplayLight_sensitive(value,unit);
            case 6:
                //紅外線
                return new ItemDisplayInfrared(value,unit);
            case 7:
                return new ItemDisplayCO(value,unit);
            case 8:
                return new ItemDisplayCO2(value,unit);
            case 9:
                //煙霧
                return new ItemDisplayFume(value,unit);
            case 10:
                //可燃氣體
                return new ItemDisplayCombustible_gas(value,unit);
            default:
                return null;
        }
    }

    public static GridItem get(int index,String name,double value,String unit){
        switch (index){
            case 1:
                //溫度
                return new ItemDisplayTemperature(value,unit);
            case 2:
                //濕度
                return new ItemDisplayHumidity(value,unit);
            case 3:
                 return new ItemDisplayPM25(value,unit);
            case 4:
                return new ItemDisplayPM10(value,unit);
            case 5:
                //光感應
                return new ItemDisplayLight_sensitive(value,unit);
            case 6:
                //紅外線
                return new ItemDisplayInfrared(value,unit);
            case 7:
                return new ItemDisplayCO(value,unit);
            case 8:
                return new ItemDisplayCO2(value,unit);
            case 9:
                //煙霧
                return new ItemDisplayFume(value,unit);
            case 10:
                //可燃氣體
                return new ItemDisplayCombustible_gas(value,unit);
            case 11:
                return new ItemControlLed(name,(int)(value));
            case 12:
                return new ItemControlFan(name,(int)(value));
            case 13:
                return new ItemControlExhaust_fan(name,(int)(value));
            default:
                return null;
        }
    }

    private static boolean i2TF(double value){
        return (value > 0.5)?true:false;
    }
}
