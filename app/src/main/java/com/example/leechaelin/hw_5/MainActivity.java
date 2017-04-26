package com.example.leechaelin.hw_5;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ListView list;
    Button b1,b2,b3,b4;
    EditText e1;
    ArrayList<Data> input = new ArrayList<Data>();
    ArrayList<Data> filteredinput = new ArrayList<Data>();
    Adapter adapter;
    boolean deleting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = (EditText)findViewById(R.id.edittext);
        list = (ListView)findViewById(R.id.listview);
        b1 = (Button)findViewById(R.id.add);
        b2 = (Button)findViewById(R.id.name);
        b3 = (Button)findViewById(R.id.cate_btn);
        b4 = (Button)findViewById(R.id.select);
        adapter = new Adapter (this,filteredinput,false);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2 = new Intent(MainActivity.this,Main3Activity.class);
                intent2.putExtra("Data",filteredinput.get(position));
                startActivityForResult(intent2,0);

            }
        });


        btn_funct();
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String search = e1.getText().toString();
                 adapter.filter(search);

            }
        });

    }
    void btn_funct(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent,5);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setAsc(0);
                adapter.notifyDataSetChanged();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //종류별로 정렬하는 거 만들기
                adapter.setAsc(1);
                adapter.notifyDataSetChanged();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleting) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("삭제확인 ")
                            .setMessage("선택한 맛집을 정말 삭제하시겠습니까 ? ")
                            .setNegativeButton("취소 ",null)
                            .setPositiveButton("확인 ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for(int i=filteredinput.size()-1;i>=0;i--){
                                        if(filteredinput.get(i).getIsCheck()==1){
                                            for(int j=input.size()-1;j>=0;j--){
                                                if(input.get(j).equals(filteredinput.get(i))){
                                                    input.remove(j);
                                                    break;
                                                }
                                            }
                                        }
                                        filteredinput.remove(i);
                                    }
                                    deleting = false;
                                    b4.setText("선택");
                                    adapter.setDel(false);
                                    adapter.notifyDataSetChanged();
                                }
                            }).show();
                }
                else{
                    deleting = true;
                    adapter.setDel(true);
                    adapter.notifyDataSetChanged();
                    b4.setText("삭제");
                }
            }
        });
    }


    @Override
    //mainactivity 2에서 준 정보
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==5){
            if(resultCode==RESULT_OK){
                Data d1 =  data.getParcelableExtra("Data");
                filteredinput.add(d1);
                adapter.notifyDataSetChanged();

            }
        }
    }

}
