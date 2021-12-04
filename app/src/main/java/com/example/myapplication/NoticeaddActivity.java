package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Network.RetrofitClient;
import com.example.myapplication.Network.ServiceApi;
import com.example.myapplication.data.NoticeData2;
import com.example.myapplication.data.NoticeResponse2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeaddActivity extends AppCompatActivity {

    String ID;
    int checkhg;
    String name;
    int connect;
    String device;

    private TextView wtitle;
    private EditText wcontents;
    private Button save;
    private ServiceApi service;

    String title,notice;
    String id,admin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticeadd);

        Intent intent = getIntent();
        ID=intent.getExtras().getString("ID") ;
        name=intent.getExtras().getString("name") ;
        checkhg=Integer.parseInt(intent.getExtras().getString("checkhg"));
        connect=Integer.parseInt(intent.getExtras().getString("connect"));
        device=intent.getExtras().getString("device");

        id=ID;
        admin= String.valueOf(checkhg);

        wtitle =(TextView) findViewById(R.id.title);
        wcontents =(EditText) findViewById(R.id.contents);
        save = (Button) findViewById(R.id.save);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savecheck();
            }
        });

    }

    private void savecheck() {
        wtitle.setError(null);
        boolean cancel = false;
        View focusView = null;
        title = wtitle.getText().toString();
        notice = wcontents.getText().toString();
        if (title.isEmpty()) {
            Toast.makeText(getApplicationContext(),"제목을 입력하세요",Toast.LENGTH_SHORT).show();
            focusView = wtitle;
            cancel = true;
        }
        else {
            if (notice.isEmpty()) {
                Toast.makeText(getApplicationContext(),"내용을 입력하세요",Toast.LENGTH_SHORT).show();
                focusView = wcontents;
                cancel = true;
            }
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            startSave(new NoticeData2(title,notice,id,admin));
        }
    }
    private void startSave(NoticeData2 data) {
        service.userNoticeAdd(data).enqueue(new Callback<NoticeResponse2>() {
            @Override
            public void onResponse(Call<NoticeResponse2> call, Response<NoticeResponse2> response) {

                NoticeResponse2 result = response.body();
                if (result.getCode() == 200) {
                    finish();
                }
            }
            @Override
            public void onFailure(Call<NoticeResponse2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "공지추가 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("공지추가 에러 발생", t.getMessage());
            }
        });
    }
}
