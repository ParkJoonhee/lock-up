package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PwActivity extends AppCompatActivity {

    final static MainActivity Main = new MainActivity();
    private static String ID = Main.ID;
    private static int checkhg = Main.checkhg;
    private static String name = Main.name;
    private static int live_code = Main.live_code;
    private static int house = Main.house;

    private TextView pwd, timer;
    private Button main, notice, pw, code, setting;
    int num;

    private Random rnd = new Random();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pw);

        pwd = (TextView) findViewById(R.id.pwd);
        timer = (TextView) findViewById(R.id.timer);
        main = (Button) findViewById(R.id.main1);
        notice =(Button) findViewById(R.id.notice);
        pw = (Button) findViewById(R.id.Pw1);
        code = (Button) findViewById(R.id.Code1);
        setting = (Button) findViewById(R.id.Setting1);

        num = 0;

        Randomnumber();

        countDown();

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
    public void Randomnumber() {

        do {
            num = rnd.nextInt(999999);
        } while (num < 100000);

        pwd.setText(Integer.toString(num));

        countDown();
    }


    public void countDown() {
        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Integer.toString((int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                Randomnumber();
            }

        }.start();
    }
}
