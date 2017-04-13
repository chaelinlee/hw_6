package com.example.leechaelin.hw_5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5, t6, t7;
    ImageView i;
    Button b1;
    Data input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();
        Intent intent = getIntent();
        input = intent.getParcelableExtra("Data");
        t1.setText(input.name);
        t2.setText(input.menu1);
        t3.setText(input.menu2);
        t4.setText(input.menu3);
        t5.setText(input.phonenum);
        t6.setText(input.homepage);
        t7.setText(input.enrolldate);
        String pointer = input.category;

        if (pointer.equals("1")) {
            i.setImageResource(R.drawable.chicken);
        } else if (pointer.equals("2")) {
            i.setImageResource(R.drawable.pizza);
        } else if (pointer.equals("3")) {
            i.setImageResource(R.drawable.hamburger);
        }

    }

    void init() {
        t1 = (TextView) findViewById(R.id.txtname);
        i = (ImageView) findViewById(R.id.imgno);
        t2 = (TextView) findViewById(R.id.etmenu1);
        t3 = (TextView) findViewById(R.id.etmenu2);
        t4 = (TextView) findViewById(R.id.etmenu3);
        t5 = (TextView) findViewById(R.id.tvTel);
        t6 = (TextView) findViewById(R.id.tvURL);
        t7 = (TextView) findViewById(R.id.tvRegdate);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnback) {
            finish();
        } else if (v.getId() == R.id.imageView2) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + input.phonenum));
            startActivity(intent);

        }else if(v.getId()==R.id.imageView3){
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+input.homepage));
            startActivity(intent);

        }
    }

}
