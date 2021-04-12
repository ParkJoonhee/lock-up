package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PwActivity extends AppCompatActivity {

    private TextView pwd, timer;
    private Button main, pw, code, setting;
    int num, time, second;

    private Random rnd = new Random();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pw);

        pwd = (TextView) findViewById(R.id.pwd);
        timer = (TextView) findViewById(R.id.timer);
        main= (Button) findViewById(R.id.main1);
        pw = (Button) findViewById(R.id.Pw1);
        code = (Button) findViewById(R.id.Code1);
        setting = (Button) findViewById(R.id.Setting1);

        num = 0;

        do {
            Randomnumber();
        } while (num < 100000);

        pwd.setText(num);

        /**/

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.MenuActivity.class);
                startActivity(intent);
            }
        });
        pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.PwActivity.class);
                startActivity(intent);
            }
        });
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.CodeActivity.class);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Randomnumber() {

        num = rnd.nextInt(999999);
    }

}
