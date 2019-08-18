package com.dev.lungyu.homecontroller.fragment.situation.items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.List;

/**
 * Created by lungyu on 2/24/18.
 */

public class GridAdapter extends BaseAdapter{

    private Context context;
    private List<GridItem> list;

    public GridAdapter(Context context,List<GridItem> source){
        this.context = context;
        this.list = source;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public GridItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);   // LayoutInflater可以幫助我們把
            convertView = inflater.inflate(getItem(position).getViewId(), null);          //  listitem中的佈局FIT返每一格的SIZE
        }

        getItem(position).initView(convertView);

        return convertView;
    }
}
