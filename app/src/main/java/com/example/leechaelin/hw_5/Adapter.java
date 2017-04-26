package com.example.leechaelin.hw_5;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

/**
 * Created by leechaelin on 2017. 4. 13..
 */

public class Adapter extends BaseAdapter {
    ArrayList<Data> input;
    ArrayList<Data> find = new ArrayList<Data>();
    Context c;
    boolean delete = false;
    public Adapter(Context c,ArrayList<Data>input,boolean delete){
        this.c = c;
        this.input = input;
        this.delete = delete;
        this.find.addAll(input);

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(c);
        if(convertView==null){
            convertView = inflater.inflate(R.layout.resturant_xml,null);
        }
        TextView t1 = (TextView)convertView.findViewById(R.id.res_name);
        TextView t2 = (TextView)convertView.findViewById(R.id.res_tel);
        ImageView i =  (ImageView)convertView.findViewById(R.id.rest_image);
        CheckBox cb = (CheckBox)convertView.findViewById(R.id.c1);

        t1.setText(input.get(position).name);
        t2.setText(input.get(position).phonenum);

        if(input.get(position).category.equals("1")){
            i.setImageResource(R.drawable.chicken);
        }else if(input.get(position).category.equals("2")){
            i.setImageResource(R.drawable.pizza);
        }else{
            i.setImageResource(R.drawable.hamburger);
        }

        //삭제하게 만들때
        if(delete){
            cb.setVisibility(View.VISIBLE);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                        input.get(position).setIsCheck(1);
                    else
                        input.get(position).setIsCheck(0);
                }
            });
        } else{
            cb.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    public void setDel(boolean delete){
        this.delete = delete;
        this.notifyDataSetChanged();
    }


    Comparator<Data> nameAsc = new Comparator<Data>() {
        @Override
        public int compare(Data o1, Data o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };


    Comparator<Data> menuAsc = new Comparator<Data>() {
        @Override
        public int compare(Data o1, Data o2) {
            return o1.category.compareTo(o2.category);
        }
    };
    final  static int NAME_ASC =0;
    final static int MENU_ASC = 1;
    public void setAsc(int stype){
        if(stype == NAME_ASC) {
            Collections.sort(input, nameAsc);
            this.notifyDataSetChanged();
        }
        else if(stype == MENU_ASC){
            Collections.sort(input,menuAsc);
            this.notifyDataSetChanged();
        }
    }


    public void filter(String search) {
        search = search.toLowerCase(Locale.getDefault());
        find.clear();

        if(search.length()==0){
            find.addAll(input);
        }else{
            for(Data d:input){
                if(d.getName().toLowerCase(Locale.getDefault()).contains(search)){
                    find.add(d);
                }
            }
        }
        this.notifyDataSetChanged();
    }
}
