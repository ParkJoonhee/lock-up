package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Network.RetrofitClient;
import com.example.myapplication.Network.ServiceApi;
import com.example.myapplication.data.GeustlistData;
import com.example.myapplication.data.GeustlistResponse;
import com.example.myapplication.data.LoginData;
import com.example.myapplication.data.LoginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HMenuActivity extends AppCompatActivity {

    final static MainActivity Main = new MainActivity();
    private static String ID = Main.ID;
    private static int checkhg = Main.checkhg;
    private static String name = Main.name;
    private static int live_code = Main.live_code;
    private static int house = Main.house;

    private Button main, notice, pw, code, setting;

    TextView r_number, GeustName;
    ListView guestlist;

    String r_number1, geustName1;

    private ProgressBar mProgressView;
    private ServiceApi service;

    ArrayList<GeustlistResponse> Geustlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hmenu);

        main = (Button) findViewById(R.id.main1);
        notice =(Button) findViewById(R.id.notice);
        pw = (Button) findViewById(R.id.Pw1);
        code = (Button) findViewById(R.id.Code1);
        setting = (Button) findViewById(R.id.Setting1);

        r_number = (TextView) findViewById(R.id.r_number);
        GeustName = (TextView) findViewById(R.id.GeustName);
        guestlist = (ListView) findViewById(R.id.guestlist);

        mProgressView = (ProgressBar) findViewById(R.id.login_progress);
        service = RetrofitClient.getClient().create(ServiceApi.class);


        this.Guestlist(new GeustlistData(name,live_code,house));

        final com.example.myapplication.data.GeustlistAdapter myAdapter = new com.example.myapplication.data.GeustlistAdapter(this,Geustlist);

        guestlist.setAdapter(myAdapter);

        guestlist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(), myAdapter.getItem(position).getR_number(),Toast.LENGTH_LONG).show();
            }
        });

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

    public void Guestlist(GeustlistData data) {
        service.userGeust(data).enqueue(new Callback<GeustlistResponse>() {
            @Override
            public void onResponse(Call<GeustlistResponse> call, Response<GeustlistResponse> response) {
                GeustlistResponse result = response.body();
                Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if(result.getCode()==200)
                {
                    Intent intent = null;

                    r_number1 = result.getR_number();
                    geustName1 = result.getGeustName();

                    Geustlist = new ArrayList<GeustlistResponse>();

                    //Geustlist.add(r_number1, geustName1);
                }
            }
            @Override
            public void onFailure(Call<GeustlistResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "목록 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("목록 에러 발생", t.getMessage());
            }
        });

    }

}