package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    final static MainActivity Main = new MainActivity();
    private static String ID = Main.ID;
    private static int checkhg = Main.checkhg;
    private static String name = Main.name;
    private static int live_code = Main.live_code;
    private static int house = Main.house;

    private Button main, notice, pw, code, setting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        main = (Button) findViewById(R.id.main1);
        notice =(Button) findViewById(R.id.notice);
        pw = (Button) findViewById(R.id.Pw1);
        code = (Button) findViewById(R.id.Code1);
        setting = (Button) findViewById(R.id.Setting1);


        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                if(checkhg == 0) {
                    intent = new Intent(getApplicationContext(), com.example.myapplication.HMenuActivity.class);
                } else if(checkhg == 1){
                    intent = new Intent(getApplicationContext(), com.example.myapplication.MenuActivity.class);
                }
                startActivity(intent);
                finish();
            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                if(checkhg == 0) {
                    intent = new Intent(getApplicationContext(), com.example.myapplication.HNoticeActivity.class);
                } else if(checkhg == 1){
                    intent = new Intent(getApplicationContext(), com.example.myapplication.GNoticeActivity.class);
                }
                startActivity(intent);
                finish();
            }
        });

        pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.PasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.CodeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
