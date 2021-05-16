package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.Network.ServiceApi;
import com.example.myapplication.data.CodeData;
import com.example.myapplication.data.CodeImport;
import com.example.myapplication.data.CodeImportData;
import com.example.myapplication.data.CodeResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodeaddActivity extends AppCompatActivity {

    final static MainActivity Main = new MainActivity();
    private static String id = Main.id;

    private Button main, notice, pw, code, setting, CodeOk;
    int checkhg, cYear, cMonth, cDay;

    private RadioButton Single, Multi;
    private EditText GuestName;
    private EditText GuestOther;
    private DatePicker Datepicker;
    private String Date, GuestName1, GuestOther1, count;

    private ProgressBar mProgressView;
    private ServiceApi service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codeadd);

        main = (Button) findViewById(R.id.main1);
        notice =(Button) findViewById(R.id.notice);
        pw = (Button) findViewById(R.id.Pw1);
        code = (Button) findViewById(R.id.Code1);
        setting = (Button) findViewById(R.id.Setting1);

        Single = (RadioButton) findViewById(R.id.Single);
        Multi = (RadioButton) findViewById(R.id.Multi);

        GuestName = (EditText) findViewById(R.id.GuestName);
        GuestOther = (EditText) findViewById(R.id.GuestOther);
        Datepicker = (DatePicker) findViewById(R.id.Datepicker);

        CodeOk = (Button) findViewById(R.id.CodeOk);

        checkhg = 0;

        Single.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Datepicker.setVisibility(view.INVISIBLE);
                Date = "X";
                count = "0";
            }
        });

        Multi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Datepicker.setVisibility(view.VISIBLE);
                Calendar cal = Calendar.getInstance();
                cYear = cal.get(Calendar.YEAR);
                cMonth = cal.get(Calendar.MONTH);
                cDay = cal.get(Calendar.DAY_OF_MONTH);

                // 처음 실행시에 설정할 내용
                Date = Integer.toString(cYear) + "-" + Integer.toString(cMonth + 1) + "-" + Integer.toString(cDay);

                count = "1";
            }
        });

        Datepicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
                Date = Integer.toString(year) + "-" + Integer.toString(monthOfYear + 1) +  "-" + Integer.toString(dayOfMonth);
            }
        });

        CodeOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), com.example.myapplication.CodeActivity.class);
                startActivity(intent);
                finish();
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

    private void readDiary(String date) {
        //startEdit2(new CodeImportData(email));
        CodeOk.setVisibility(View.INVISIBLE);
        showProgress(true);
    }


    private void startEdit2(CodeImportData data) {
        service.userCode2(data).enqueue(new Callback<CodeImport>() {
            @Override
            public void onResponse(Call<CodeImport> call, Response <CodeImport> response){
                CodeImport result = response.body();
                if(!result.getGuestName().isEmpty())
                {
                    GuestName1 = result.getGuestName();
                    GuestName.setText(GuestName1);
                    GuestOther1 = result.getGuestOther();
                    GuestOther.setText(GuestOther1);
                } else {
                    Toast.makeText(com.example.myapplication.CodeaddActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    GuestName.setText(null);
                    GuestOther.setText(null);
                }
                showProgress(false);
                CodeOk.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<CodeImport> call, Throwable t) {
                Toast.makeText(com.example.myapplication.CodeaddActivity.this, "작성 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("작성 에러 발생", t.getMessage());
                showProgress(false);
                CodeOk.setVisibility(View.VISIBLE);
            }
        });
    }

    private void attemptcode() {
        GuestName.setError(null);
        GuestOther.setError(null);

        GuestName1 = GuestName.getText().toString();
        GuestOther1 = GuestOther.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 이름의 유효성 검사
        if (GuestName1.isEmpty()) {
            GuestName.setError("이름을 입력해주세요.");
            focusView = GuestName;
            cancel = true;
        } else if (!istitleValid(GuestName1)) {
            GuestName.setError("영문 50자, 한글 25자 이하의 이름를 입력해주세요.");
            focusView = GuestName;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();

        } else {
            startEdit(new CodeData(id, count, Date, GuestName1, GuestOther1));
            CodeOk.setVisibility(View.INVISIBLE);
            showProgress(true);
        }
    }

    private void startEdit(CodeData data) {
        service.userCode(data).enqueue(new Callback<CodeResponse>() {
            @Override
            public void onResponse(Call < CodeResponse > call, Response < CodeResponse > response){
                CodeResponse result = response.body();
                Toast.makeText(com.example.myapplication.CodeaddActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);
                CodeOk.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<CodeResponse> call, Throwable t) {
                Toast.makeText(com.example.myapplication.CodeaddActivity.this, "작성 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("작성 에러 발생", t.getMessage());
                showProgress(false);
                CodeOk.setVisibility(View.VISIBLE);
            }
        });
    }

    private boolean istitleValid(String title) {
        return title.length() <= 50;
    }
    private boolean iscontentValid(String content) {
        return content.length() <= 1000;
    }
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
