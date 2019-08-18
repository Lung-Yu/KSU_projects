package com.dev.lungyu.homecontroller.fragment.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by lungyu on 2/23/18.
 */

public abstract class BasePageFragment extends Fragment {
    private static String TAG = "BasePageFragment";

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

    protected static int DELAY_TIME = 60*1000;
    protected boolean isReload;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isReload) {
                Log.d(TAG, "reload fetch data.");
                fetchData();
                handler.postDelayed(runnable, DELAY_TIME);
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        isReload = true;
        handler.post(runnable);
    }

    @Override
    public void onStop() {
        isReload = false;
        super.onStop();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    public abstract void fetchData();

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }
}

