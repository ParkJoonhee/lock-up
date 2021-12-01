package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HgNoticeActivity extends AppCompatActivity {

    String ID;
    int checkhg;
    String name;

    private Button hbtn, gbtn, main, notice, pw, code, setting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hgnotice);

        hbtn = (Button) findViewById(R.id.hbtn);
        gbtn = (Button) findViewById(R.id.gbtn);

        main = (Button) findViewById(R.id.main1);
        notice =(Button) findViewById(R.id.notice);
        pw = (Button) findViewById(R.id.Pw1);
        code = (Button) findViewById(R.id.Code1);
        setting = (Button) findViewById(R.id.Setting1);

        Intent intent = getIntent();
        ID=intent.getExtras().getString("ID") ;
        name=intent.getExtras().getString("name") ;
        checkhg=Integer.parseInt(intent.getExtras().getString("checkhg"));


        hbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.HNoticeActivity.class);
                intent.putExtra("ID", ID);
                intent.putExtra("name", name);
                intent.putExtra("checkhg", String.valueOf(checkhg)) ;
                startActivity(intent);
                finish();
            }
        });

        gbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.HgNoticeActivity.class);
                intent.putExtra("ID", ID);
                intent.putExtra("name", name);
                intent.putExtra("checkhg", String.valueOf(checkhg)) ;
                startActivity(intent);
                finish();
            }
        });


        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                if(checkhg == 0) {
                    intent = new Intent(getApplicationContext(), com.example.myapplication.MenuActivity.class);
                } else if(checkhg == 1){
                    intent = new Intent(getApplicationContext(), com.example.myapplication.HMenuActivity.class);
                }
                intent.putExtra("ID", ID) ;
                intent.putExtra("name", name) ;
                intent.putExtra("checkhg", String.valueOf(checkhg)) ;
                startActivity(intent);
                finish();
            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                if(checkhg == 0) {
                    intent = new Intent(getApplicationContext(), com.example.myapplication.GNoticeActivity.class);
                } else if(checkhg == 1){
                    intent = new Intent(getApplicationContext(), com.example.myapplication.HNoticeActivity.class);
                }
                intent.putExtra("ID", ID) ;
                intent.putExtra("name", name) ;
                intent.putExtra("checkhg", String.valueOf(checkhg)) ;
                startActivity(intent);
                finish();
            }
        });

        pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
                intent.putExtra("ID", ID) ;
                intent.putExtra("name", name) ;
                intent.putExtra("checkhg", String.valueOf(checkhg)) ;
                startActivity(intent);
                finish();
            }
        });
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.CodeActivity.class);
                intent.putExtra("ID", ID) ;
                intent.putExtra("name", name) ;
                intent.putExtra("checkhg", String.valueOf(checkhg)) ;
                startActivity(intent);
                finish();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.SettingActivity.class);
                intent.putExtra("ID", ID) ;
                intent.putExtra("name", name) ;
                intent.putExtra("checkhg", String.valueOf(checkhg)) ;
                startActivity(intent);
                finish();
            }
        });
    }

}