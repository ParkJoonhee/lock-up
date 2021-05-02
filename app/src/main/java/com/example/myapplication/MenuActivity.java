package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Network.ServiceApi;

import com.example.myapplication.data.NoticelistData;
import com.example.myapplication.data.NoticelistImport;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    private Button lock, main, notice, pw, code, setting;
    int checkhg;
    private String ntitle, ndate;
    private TextView Notitle, Nodate;
    private ListView Noticelist;

    private ProgressBar mProgressView;
    private ServiceApi service;

    ArrayList<NoticelistImport> noticeDatalist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        lock = (Button) findViewById(R.id.Lock);
        Notitle = (TextView) findViewById(R.id.ntitle);
        Nodate = (TextView) findViewById(R.id.ndate);
        Noticelist = (ListView) findViewById(R.id.Noticelist);
        main = (Button) findViewById(R.id.main1);
        notice =(Button) findViewById(R.id.notice);
        pw = (Button) findViewById(R.id.Pw1);
        code = (Button) findViewById(R.id.Code1);
        setting = (Button) findViewById(R.id.Setting1);

        checkhg = 0;
/*
        final MyAdapter myAdapter = new MyAdapter(this,noticeDatalist);
        Noticelist.setAdapter(myAdapter);


        noticestart(new NoticelistData(ntitle,ndate));
*/
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
/*
        Noticelist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(), myAdapter.getItem(position).getNtitle(), Toast.LENGTH_LONG).show();
            }
        });
*/

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

    public class MyAdapter extends BaseAdapter {

        Context mContext = null;
        LayoutInflater mLayoutInflater = null;
        ArrayList<NoticelistImport> sample;

        public MyAdapter(Context context, ArrayList<NoticelistImport> data) {
            mContext = context;
            sample = data;
            mLayoutInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return sample.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public NoticelistImport getItem(int position) {
            return sample.get(position);
        }

        @Override
        public View getView(int position, View converView, ViewGroup parent) {
            View view = mLayoutInflater.inflate(R.layout.noticelist, null);

            TextView movieName = (TextView)view.findViewById(R.id.ntitle);
            TextView grade = (TextView)view.findViewById(R.id.ndate);

            movieName.setText(sample.get(position).getNtitle());
            grade.setText(sample.get(position).getNdate());

            return view;
        }


    }

    private void noticestart(NoticelistData data) {
        service.userNotice(data).enqueue(new Callback<NoticelistImport>() {
            @Override
            public void onResponse(Call < NoticelistImport > call, Response < NoticelistImport > response){
                NoticelistImport result = response.body();
                if(!result.getNtitle().isEmpty())
                {
                    ntitle = result.getNtitle();
                    Notitle.setText(ntitle);
                    ndate = result.getNdate();
                    Nodate.setText(ndate);
                } else {
                    Toast.makeText(MenuActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    Notitle.setText(null);
                    Nodate.setText(null);
                }
                showProgress(false);

            }

            @Override
            public void onFailure(Call<NoticelistImport> call, Throwable t) {
                Toast.makeText(com.example.myapplication.MenuActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
