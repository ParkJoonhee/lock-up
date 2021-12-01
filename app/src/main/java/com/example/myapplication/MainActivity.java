package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Network.RetrofitClient;
import com.example.myapplication.Network.ServiceApi;

import com.example.myapplication.data.LoginData;
import com.example.myapplication.data.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText joinID1;
    private EditText joinPw1;
    private Button login1;
    private Button join;
    private ProgressBar mProgressView;
    private ServiceApi service;
    int checkhg = 2;
    String ID, name;
    boolean cancel;
    Intent intent = null;
    String code;


    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joinID1 = (EditText) findViewById(R.id.joinID1);
        joinPw1 = (EditText) findViewById(R.id.joinPw1);
        login1 = (Button) findViewById(R.id.login1);
        join = (Button) findViewById(R.id.join);
        mProgressView = (ProgressBar) findViewById(R.id.login_progress);

        service = RetrofitClient.getClient().create(ServiceApi.class);


        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinID1.setError(null);
                joinPw1.setError(null);
                id = joinID1.getText().toString();
                String password = joinPw1.getText().toString();
                View focusView = null;
                cancel = false;
                // 패스워드의 유효성 검사
                if (password.isEmpty()) {
                    joinID1.setError("비밀번호를 입력해주세요.");
                    focusView = joinID1;
                    cancel = true;
                } else if (!isPasswordValid(password)) {
                    joinPw1.setError("6자 이상의 비밀번호를 입력해주세요.");
                    focusView = joinPw1;
                    cancel = true;
                }
                // 이메일의 유효성 검사
                if (id.isEmpty()) {
                    joinID1.setError("아이디를 입력해주세요.");
                    focusView = joinID1;
                    cancel = true;
                }
                if (cancel) {
                    focusView.requestFocus();
                } else {
                    service.userLogin(new LoginData(id, password)).enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                            LoginResponse result = response.body();
                            Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);
                            code = String.valueOf(result.getCode());
                            if (code.equals("200")) {
                                ID = result.getId();
                                name = result.getName();
                                checkhg = result.getAdmin();
                                intent = new Intent(getApplicationContext(), com.example.myapplication.MenuActivity.class);
                                intent.putExtra("ID", ID) ;
                                intent.putExtra("name", name) ;
                                intent.putExtra("checkhg", String.valueOf(checkhg)) ;
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                            Log.e("로그인 에러 발생", t.getMessage());
                            showProgress(false);
                        }
                    });
                    showProgress(true);
                }
            }
        });
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.JoinActivity.class);
                startActivity(intent);
            }
        });
    }
    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}