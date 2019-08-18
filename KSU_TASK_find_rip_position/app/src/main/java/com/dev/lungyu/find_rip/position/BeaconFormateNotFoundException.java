package com.dev.lungyu.find_rip.position;

/**
 * Created by lungyu on 8/29/17.
 */

public class BeaconFormateNotFoundException extends Exception {
    public BeaconFormateNotFoundException(String message){
        super("parser error:" + message);
    }
}
