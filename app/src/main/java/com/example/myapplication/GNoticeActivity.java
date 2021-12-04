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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GNoticeActivity extends AppCompatActivity {

    String ID;
    int checkhg;
    String name;
    int connect;
    String device;

    private ListView Noticelist;
    private ServiceApi service;

    private Button hbtn, gbtn, main, notice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ghnotice);

        hbtn = (Button) findViewById(R.id.hbtn);
        gbtn = (Button) findViewById(R.id.gbtn);

        main = (Button) findViewById(R.id.main1);
        notice =(Button) findViewById(R.id.notice);


        Intent intent = getIntent();
        ID=intent.getExtras().getString("ID") ;
        name=intent.getExtras().getString("name") ;
        checkhg=Integer.parseInt(intent.getExtras().getString("checkhg"));
        connect=Integer.parseInt(intent.getExtras().getString("connect"));
        device=intent.getExtras().getString("device");


        service = RetrofitClient.getClient().create(ServiceApi.class);
        Noticelist = (ListView)findViewById(R.id.Noticelist);
        String admin="1";

        service.userNoticeList(new NoticeData(admin)).enqueue(new Callback<NoticeResponse>() {
            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {
                NoticeResponse result = response.body();
                if (result.getCode() == 200) {
                    NoticeAdapter adapter = new NoticeAdapter();
                    Noticelist.setAdapter(adapter);
                    for(NoticeResponse.Row item:result.getRows()){
                        String date=item.getDate().split("T")[0];
                        adapter.addItem(item.getTitle(),date,item.getName(),item.getNotice());
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "공지사항 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("공지사항 에러 발생", t.getMessage());
            }
        });


        hbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.GNoticeActivity.class);
                intent.putExtra("ID", ID);
                intent.putExtra("name", name);
                intent.putExtra("checkhg", String.valueOf(checkhg)) ;
                intent.putExtra("connect", String.valueOf(connect)) ;
                intent.putExtra("device", device);
                startActivity(intent);
                overridePendingTransition(0, 0);
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
                intent.putExtra("connect", String.valueOf(connect)) ;
                intent.putExtra("device", device);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });


        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(getApplicationContext(), com.example.myapplication.MenuActivity.class);
                intent.putExtra("ID", ID) ;
                intent.putExtra("name", name) ;
                intent.putExtra("checkhg", String.valueOf(checkhg)) ;
                intent.putExtra("connect", String.valueOf(connect)) ;
                intent.putExtra("device", device);
                startActivity(intent);
                overridePendingTransition(0, 0);
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
                intent.putExtra("connect", String.valueOf(connect)) ;
                intent.putExtra("device", device);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

}
