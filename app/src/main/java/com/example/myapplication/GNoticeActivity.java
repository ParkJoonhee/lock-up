package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Network.RetrofitClient;
import com.example.myapplication.Network.ServiceApi;
import com.example.myapplication.data.NoticeData;
import com.example.myapplication.data.NoticeResponse;
import com.example.myapplication.data.Row;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GNoticeActivity extends AppCompatActivity {

    String ID;
    int checkhg;
    String name;
    private ListView Noticelist;
    private ServiceApi service;

    private Button hbtn, gbtn, main, notice, pw, code, setting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ghnotice);

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


        //
        service = RetrofitClient.getClient().create(ServiceApi.class);
        Noticelist = (ListView)findViewById(R.id.Noticelist);
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss", Locale.ENGLISH);
        String admin="1";

        NoticeAdapter adapter = new NoticeAdapter();
        Noticelist.setAdapter(adapter);
        adapter.addItem("제목", "2021-09-05","홍길동","내용");
        service.userNoticeList(new NoticeData(admin)).enqueue(new Callback<NoticeResponse>() {
            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {
                NoticeResponse result = response.body();
                for(NoticeResponse.Row item:result.getRows()){
                    //String date=format.format(item.getDate());
                    String date=item.getDate();
                    Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
                    adapter.addItem(item.getTitle(), date,item.getName(),item.getNotice());
                }


            }

            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "공지사항 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("공지사항 에러 발생", t.getMessage());
            }
        });
        adapter.notifyDataSetChanged();
        //

        hbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.GNoticeActivity.class);
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
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.GgNoticeActivity.class);
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
