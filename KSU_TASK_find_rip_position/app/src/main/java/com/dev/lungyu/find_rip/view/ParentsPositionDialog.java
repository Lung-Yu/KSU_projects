package com.dev.lungyu.find_rip.view;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dev.lungyu.find_rip.R;
import com.dev.lungyu.find_rip.util.Preferences;


/**
 * Created by lungyu on 9/4/17.
 */

public class ParentsPositionDialog {
    private final Dialog _dialog;

    private EditText _edit_layer;
    private EditText _edit_x;
    private EditText _edit_y;
    private Button _btnOK;
    private Preferences preferences;
    private OnClickSaveListener onClickSaveListener = new OnClickSaveListener() {

        @Override
        public void onClick() {

        }
    };
    public ParentsPositionDialog(Context context){
        // Create custom dialog object
        _dialog = new Dialog(context);
        preferences = new Preferences(context);

        init();
    }

    public ParentsPositionDialog(Context context,OnClickSaveListener listener){
        this(context);
        this.onClickSaveListener = listener;
    }

    public void show(){
        _dialog.show();
    }

   private void init(){
       // Include dialog.xml file
       _dialog.setContentView(R.layout.dialog_tombstone);

       // set values for custom dialog components - text, image and button
       _edit_layer = (EditText) _dialog.findViewById(R.id.textview1);
       _edit_x = (EditText) _dialog.findViewById(R.id.textview2);
       _edit_y= (EditText) _dialog.findViewById(R.id.textview3);

       _edit_layer.setText(preferences.getTargetPositionLayer());
       _edit_x.setText(String.valueOf(preferences.getTargetPositionX()));
       _edit_y.setText(String.valueOf(preferences.getTargetPositionY()));

       _btnOK = (Button) _dialog.findViewById(R.id.button_ok);
       _btnOK.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onInfomationUpdate();
               _dialog.dismiss();
               onClickSaveListener.onClick();
           }
       });
   }

   private void onInfomationUpdate(){
       preferences.setTargetPositionLayer(_edit_layer.getText().toString());
       preferences.setTargetPositionX(Integer.parseInt(_edit_x.getText().toString()));
       preferences.setTargetPositionY(Integer.parseInt(_edit_y.getText().toString()));
   }

   public void setOnClickSaveListener(OnClickSaveListener listener){
       this.onClickSaveListener = listener;
   }

   public interface OnClickSaveListener{
       void onClick();
   }
}



