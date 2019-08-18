package com.dev.lungyu.homecontroller.tools.dialog;

import android.app.ProgressDialog;
import android.content.Context;

import com.dev.lungyu.homecontroller.R;

/**
 * Created by lungyu on 2/24/18.
 */

public class LoadingProcessDialog implements IDialog{
    private Context context;
    private ProgressDialog dialog;

    public LoadingProcessDialog(Context context){
        this.context = context;

    }

    public void show(){
        this.dialog = ProgressDialog.show(context,
                context.getResources().getString(R.string.dialog_process_title),
                context.getResources().getString(R.string.dialog_process_detail)
                ,true);
    }

    public void dismiss(){
        this.dialog.dismiss();
    }
}
