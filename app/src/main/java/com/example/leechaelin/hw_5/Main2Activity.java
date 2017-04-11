package com.example.leechaelin.hw_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    RadioButton r1,r2,r3;
    Data d1;
    String cate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }
    void init(){
        e1= (EditText)findViewById(R.id.etname);
        e2= (EditText)findViewById(R.id.ettel);
        e3= (EditText)findViewById(R.id.etmenu1);
        e4= (EditText)findViewById(R.id.etmenu2);
        e5= (EditText)findViewById(R.id.etmenu3);
        e6= (EditText)findViewById(R.id.etaddr);
        r1 = (RadioButton)findViewById(R.id.radio1);
        r2 = (RadioButton)findViewById(R.id.radio2);
        r3 = (RadioButton)findViewById(R.id.radio3);

    }

    public void onClick(View v){
        if(v.getId()==R.id.btnAdd){
            String name = e1.getText().toString();
            String tel = e2.getText().toString();
            String menu1 = e3.getText().toString();
            String menu2 = e4.getText().toString();
            String menu3 = e5.getText().toString();
            String homepage = e6.getText().toString();
            String enroll = new SimpleDateFormat("yy/MM/dd HH:mm:ss").format(new Date(System.currentTimeMillis()));

            if(r1.isChecked()){
                cate = "1";
            }else if(r2.isChecked()){
                cate = "2";
            }else if(r3.isChecked()){
                cate = "3";
            }
            d1 = new Data (name,tel,menu1,menu2,menu3,homepage,enroll,cate);

            Intent intent = new Intent();
            intent.putExtra("Data",d1);
            setResult(RESULT_OK,intent);

            Toast.makeText(getApplicationContext(),"등록이 완료 되었습니다 .",Toast.LENGTH_SHORT).show();
            finish();
        }else if(v.getId()==R.id.btnCancel){
            finish();
        }
    }
}
