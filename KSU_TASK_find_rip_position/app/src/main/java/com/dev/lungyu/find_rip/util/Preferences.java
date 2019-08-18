package com.dev.lungyu.find_rip.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lungyu on 9/5/17.
 */

public class Preferences {

    private static final String PREFS_NAME = "MyPrefsFile";

    private Context context;
    private SharedPreferences settings;
    public Preferences(Context context){
        this.context = context;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }


    private static final  String TARGET_POSITION_LAYER = "target_layer";
    public void setTargetPositionLayer(String layer){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(TARGET_POSITION_LAYER,layer);

        // Commit the edits!
        editor.commit();
    }

    public String getTargetPositionLayer(){
        return settings.getString(TARGET_POSITION_LAYER, "");
    }

    private static final  String TARGET_POSITION_X = "target_x";
    public void setTargetPositionX(int x){
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(TARGET_POSITION_X,x);

        // Commit the edits!
        editor.commit();
    }

    public int getTargetPositionX(){
        int res = settings.getInt(TARGET_POSITION_X, 0);
        return res;
    }

    private static final  String TARGET_POSITION_Y = "target_y";
    public void setTargetPositionY(int y){
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(TARGET_POSITION_Y,y);

        // Commit the edits!
        editor.commit();
    }
    public int getTargetPositionY(){
        return settings.getInt(TARGET_POSITION_Y, 0);
    }


}
