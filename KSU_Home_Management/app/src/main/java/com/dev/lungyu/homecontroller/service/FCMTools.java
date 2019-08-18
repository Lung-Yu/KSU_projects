package com.dev.lungyu.homecontroller.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by lungyu on 2/23/18.
 */

public class FCMTools {

    private static final String TOPIC = "news";

    public FCMTools(){
        getToken();
        setTopic(TOPIC);
    }

    private void getToken(){
        String token = FirebaseInstanceId.getInstance().getToken();
    }

    private void setTopic(String topic){
        FirebaseMessaging.getInstance().subscribeToTopic(topic);
    }
}
