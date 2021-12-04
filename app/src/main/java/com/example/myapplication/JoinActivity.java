package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Network.RetrofitClient;
import com.example.myapplication.Network.ServiceApi;

import com.example.myapplication.data.JoinData;
import com.example.myapplication.data.JoinData2;

import com.example.myapplication.data.JoinResponse;
import com.example.myapplication.data.JoinResponse2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    private RadioButton radioArray[] = new RadioButton[2];
    private int admin = 0;

    private EditText joinName;
    private EditText joinID2;
    private EditText joinPw2;
    private EditText joinPw3;
    private TextView idchecktext;
    private Button idcheck, joinOk;

    private ProgressBar mProgressView;
    private ServiceApi service;

    private String checkid = " ";
    private String checkid2 = " ";
    private String id;
    boolean check = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        radioArray[0] = (RadioButton) findViewById(R.id.User);
        radioArray[1] = (RadioButton) findViewById(R.id.Manager);

        joinName= (EditText) findViewById(R.id.joinName);
        joinID2 = (EditText) findViewById(R.id.joinID2);
        joinPw2 = (EditText) findViewById(R.id.joinPw2);
        joinPw3 = (EditText) findViewById(R.id.joinPw3);
        idchecktext = (TextView) findViewById(R.id.idchecktext);
        idcheck = (Button) findViewById(R.id.idcheck);
        joinOk = (Button) findViewById(R.id.joinOk);
        mProgressView = (ProgressBar) findViewById(R.id.join_progress);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        radioArray[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                admin = 0;

            }
        });
        radioArray[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                admin = 1;

            }
        });

        joinID2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                checkid2 = joinID2.getText().toString();
                if(checkid2.equals("")) {
                    checkid = " ";
                    idchecktext.setText("");
                    check = false;
                } else if(!checkid2.equals(checkid)) {
                    checkid = " ";
                    idchecktext.setText("중복확인을 해주십시오.");
                    check = false;
                }
            }
        });
        idcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptJoin2();
            }
        });

        joinOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptJoin();
            }
        });
    }
    private void attemptJoin2() {
        joinID2.setError(null);
        id = joinID2.getText().toString();
        checkid = id;

        boolean cancel = false;
        View focusView = null;

        // ID의 유효성 검사
        if (id.isEmpty()) {
            joinID2.setError("ID을 입력해주세요.");
            focusView = joinID2;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin2(new JoinData2(id));
            showProgress(true);
        }

    }

    private void startJoin2(JoinData2 data) {
        service.userJoin2(data).enqueue(new Callback<JoinResponse2>() {
            View focusView = null;
            @Override
            public void onResponse(Call<JoinResponse2> call, Response<JoinResponse2> response) {
                JoinResponse2 result = response.body();
                if(result.getCode()==200) {
                    check = true;
                    joinID2.setError(null);
                    focusView = joinID2;
                    idchecktext.setText(result.getMessage());
                } else if(result.getCode()==201) {
                    joinID2.setError(result.getMessage());
                    focusView = joinID2;
                }
                showProgress(false);
            }
            @Override
            public void onFailure(Call<JoinResponse2> call, Throwable t) {
                Toast.makeText(com.example.myapplication.JoinActivity.this, "회원가입 에러 발생(ID)", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생(ID)", t.getMessage());
                showProgress(false);
            }
        });
    }

    ;
    private void attemptJoin() {
        joinID2.setError(null);
        joinPw2.setError(null);
        joinPw3.setError(null);
        joinName.setError(null);

        String id = joinID2.getText().toString();
        String password = joinPw2.getText().toString();
        String password2 = joinPw3.getText().toString();
        String name = joinName.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            joinPw2.setError("비밀번호를 입력해주세요.");
            focusView = joinPw2;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            joinPw2.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = joinPw2;
            cancel = true;
        }

        // 2차 패스워드의 유효성 검사
        if (password2.isEmpty()) {
            joinPw3.setError("2차 비밀번호를 입력해주세요.");
            focusView = joinPw3;
            cancel = true;
        } else if (!password2.equals(password)) {
            joinPw3.setError("비밀번호가 다릅니다.");
            focusView = joinPw3;
            cancel = true;
        }

        // ID의 유효성 검사
        if (id.isEmpty()) {
            joinID2.setError("ID을 입력해주세요.");
            focusView = joinID2;
            cancel = true;
        }

        // 이름의 유효성 검사
        if (name.isEmpty()) {
            joinName.setError("이름을 입력해주세요.");
            focusView = joinName;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else if (!check) {
            joinID2.setError("ID 중복확인을 해주세요.");
            focusView = joinID2;
        }
        else {
            startJoin(new JoinData(admin, id, password, name));
            showProgress(true);
        }
    }
    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {

                JoinResponse result = response.body();
                Toast.makeText(com.example.myapplication.JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);
                if (result.getCode() == 200) {
                    finish();
                }
            }
            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(com.example.myapplication.JoinActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }
    private boolean isPasswordValid(String joinPw2) {
        return joinPw2.length() >= 6;
    }
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
