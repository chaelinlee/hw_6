package com.example.leechaelin.hw_5;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    ListView list;
    ArrayList<Data> input = new ArrayList<Data>();
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list = (ListView)findViewById(R.id.listview);
        adapter = new Adapter (this,input);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2 = new Intent(MainActivity.this,Main3Activity.class);
                intent2.putExtra("Data",input.get(position));
                startActivityForResult(intent2,0);

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setMessage("정말로 삭제하시겠습니까 ?");
                dlg.setNegativeButton("닫기",null);
                dlg.setPositiveButton("확인 ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        input.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),"삭제하였습니다 ",Toast.LENGTH_SHORT).show();

                    }
                }).show();



                return true;
            }
        });
    }

    public void onClick(View v){
        if(v.getId()==R.id.add){
            Intent intent = new Intent(this,Main2Activity.class);
            startActivityForResult(intent,5);
        }else if(v.getId()==R.id.name){
            Collections.sort(input,nameAsc);
            adapter.notifyDataSetChanged();
        }


    }

    @Override
    //mainactivity 2에서 준 정보
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==5){
            if(resultCode==RESULT_OK){
                Data d1 =  data.getParcelableExtra("Data");
                input.add(d1);
                adapter.notifyDataSetChanged();

            }
        }
    }

    Comparator<Data> nameAsc = new Comparator<Data>() {
        @Override
        public int compare(Data o1, Data o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

}
