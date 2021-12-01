package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.example.myapplication.data.GeustlistAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HMenuActivity extends AppCompatActivity {

    String ID;
    int checkhg;
    String name;

    private Button main, notice, pw, code, setting;

    TextView House, GeustName;
    ListView guestlist;

    String geustName1;


    private ProgressBar mProgressView;
    private ServiceApi service;


    private GeustlistAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hmenu);

        main = (Button) findViewById(R.id.main1);
        notice =(Button) findViewById(R.id.notice);
        pw = (Button) findViewById(R.id.Pw1);
        code = (Button) findViewById(R.id.Code1);
        setting = (Button) findViewById(R.id.Setting1);

        House = (TextView) findViewById(R.id.House);
        GeustName = (TextView) findViewById(R.id.GeustName);
        guestlist = (ListView) findViewById(R.id.guestlist);

        mProgressView = (ProgressBar) findViewById(R.id.login_progress);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        Intent intent = getIntent();
        ID=intent.getExtras().getString("ID") ;
        name=intent.getExtras().getString("name") ;
        checkhg=Integer.parseInt(intent.getExtras().getString("checkhg"));

/*
        myAdapter = new GeustlistAdapter();

        guestlist.setAdapter(myAdapter);

        this.Guestlist(new GeustlistData(ID));

        guestlist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(), myAdapter.getItem(position).getHouse(),Toast.LENGTH_LONG).show();
            }
        });
*/
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

    public void Guestlist(GeustlistData data) {
        service.userGeust(data).enqueue(new Callback<GeustlistResponse>() {
            @Override
            public void onResponse(Call<GeustlistResponse> call, Response<GeustlistResponse> response) {
                GeustlistResponse result = response.body();
                if(result.getCode()==200)
                {
                    Intent intent = null;

                    geustName1 = result.getGeustName();


                    //myAdapter.addItem(live_code1, House1, geustName1);
                }
            }
            @Override
            public void onFailure(Call<GeustlistResponse> call, Throwable t) {
                Toast.makeText(HMenuActivity.this, "목록 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("목록 에러 발생", t.getMessage());
            }
        });

    }

}