package com.dev.lungyu.find_rip.position;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lungyu on 8/29/17.
 */

public class Ancestors {
    private List<Beacon> beaconList;

    private float x_ = 20;
    private float y_ = 16;

    private float x;
    private float y;

    public void calc(){
        GaussianElimination(_row, _col) ;
        show(_row,_col);
    }


    public Ancestors(){
        beaconList = new LinkedList<Beacon>();
    }

    public void addBeacon(Beacon beacon){
        beaconList.add(beacon);
    }


    /*
    * aj = x1 - xj
    * bj = y1 - yj
    * cj = x1^2 - xj^2 + y1^2 - yj^2 - d^2 -dj^2
    * */

    public void translate(){
        //buildSamples();
        //List2Array();
        _row = beaconList.size()-1;
        _col = 3;
        _arr = new float[_row *_col];

        for(int i=1;i<beaconList.size();i++){
            float aj = beaconList.get(0).getX() - beaconList.get(i).getX();
            float bj = beaconList.get(0).getY() - beaconList.get(i).getY();
            double cj = Math.pow(beaconList.get(0).getX(),2) - Math.pow(beaconList.get(i).getX(),2)
                    + Math.pow(beaconList.get(0).getY(),2) - Math.pow(beaconList.get(i).getY(),2)
                    - Math.pow(beaconList.get(0).distance(),2) - Math.pow(beaconList.get(i).distance(),2);

            _arr[(i-1)*_col + 0] = aj;
            _arr[(i-1)*_col + 1] = bj;
            _arr[(i-1)*_col + 2] =(float) cj;
        }

        System.out.println("finsh.");
    }


    private float[] _arr;
    private int _col;
    private int _row;
//    private void List2Array(){
//
//        _row = arrays.size();
//        if(_row!=0)
//            _col = arrays.get(0).size();
//
//
//        _arr = new float[_col * _row];
//        for(int i=0;i<_row;i++)
//            for(int j=0;j<_col;j++)
//                _arr[i * _col + j] = arrays.get(i).get(j);
//
//    }

    private void GaussianElimination( int col_n, int row_n )
    {
        /* left-down to zero */
        for(int i = 0; i < col_n; i++) {
            for(int j = i + 1; j < row_n; j++) {
                if((j*row_n+i) >= _arr.length)  continue;

                float enlarge = _arr[j*row_n+i] / _arr[i*row_n+i];
                for(int k=col_n;k>i-1;k--){
                    _arr[j*row_n+k] = _arr[j * row_n + k] - (enlarge * _arr[i*row_n+k]);
                }
            }
        }

        /* diagonal to one */
        for(int i = 0; i < col_n; i++) {
            float tmp = _arr[i*row_n+i];
            for(int j = i; j < row_n; j++) {
                _arr[i*row_n+j] = _arr[i*row_n+j] / tmp;
            }
        }

        /* right-up to zero */
        for(int j = row_n - 2; j > 0; j--) {
            for(int i = 0; i < j; i++) {
                _arr[i*row_n+row_n-1] = _arr[i*row_n+row_n-1] - _arr[i*row_n+j] * _arr[j*row_n+row_n-1];
            }
        }
    }


    private void show(int col_n,int row_n) {
        System.out.println("-----------show------------");
        for(int i = 0; i < col_n; i++) {
            System.out.println(_arr[i*row_n+(row_n-1)]);
        }
        System.out.println("-----------show------------");

        this.x = _arr[0*row_n+(row_n-1)];
        this.y = _arr[1*row_n+(row_n-1)];
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
