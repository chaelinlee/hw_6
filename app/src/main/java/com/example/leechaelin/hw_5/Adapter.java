package com.example.leechaelin.hw_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by leechaelin on 2017. 4. 13..
 */

public class Adapter extends BaseAdapter {
    ArrayList<Data> input;
    Context c;
    public Adapter(Context c,ArrayList<Data>input){
        this.c = c;
        this.input = input;
    }
    @Override
    public int getCount() {
        return input.size();
    }

    @Override
    public Object getItem(int position) {
        return input.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(c);
        if(convertView==null){
            convertView = inflater.inflate(R.layout.resturant_xml,null);
        }
        TextView t1 = (TextView)convertView.findViewById(R.id.res_name);
        TextView t2 = (TextView)convertView.findViewById(R.id.res_tel);
        ImageView i =  (ImageView)convertView.findViewById(R.id.rest_image);
        t1.setText(input.get(position).name);
        t2.setText(input.get(position).phonenum);
        if(input.get(position).category.equals("1")){
            i.setImageResource(R.drawable.chicken);
        }else if(input.get(position).category.equals("2")){
            i.setImageResource(R.drawable.pizza);
        }else{
            i.setImageResource(R.drawable.hamburger);
        }

        return convertView;
    }


}
