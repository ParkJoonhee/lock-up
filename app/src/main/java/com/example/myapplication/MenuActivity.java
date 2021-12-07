package com.example.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Network.RetrofitClient;
import com.example.myapplication.Network.ServiceApi;

import com.example.myapplication.data.NoticeData;
import com.example.myapplication.data.NoticeResponse;
import com.example.myapplication.data.OpenData;
import com.example.myapplication.data.OpenResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    String ID;
    int checkhg;
    String name;
    int connect=0;
    String device;
    int conn=0;

    private Button Close,Open, main, notice;
    private ListView Noticelist;

    private ServiceApi service;

    private String open="0";
    private String close="1";


    private static final int REQUEST_ENABLE_BT = 10; // 블루투스 활성화 상태
    private BluetoothAdapter bluetoothAdapter; // 블루투스 어댑터
    private Set<BluetoothDevice> devices; // 블루투스 디바이스 데이터 셋
    private BluetoothDevice bluetoothDevice; // 블루투스 디바이스
    private BluetoothSocket bluetoothSocket = null; // 블루투스 소켓
    private OutputStream outputStream = null; // 블루투스에 데이터를 출력하기 위한 출력 스트림
    private InputStream inputStream = null; // 블루투스에 데이터를 입력하기 위한 입력 스트림



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Close = (Button) findViewById(R.id.close);
        Open = (Button) findViewById(R.id.open);
        Noticelist = (ListView) findViewById(R.id.Noticelist);
        main = (Button) findViewById(R.id.main1);
        notice =(Button) findViewById(R.id.notice);

        Intent intent = getIntent();
        ID=intent.getExtras().getString("ID") ;
        name=intent.getExtras().getString("name") ;
        checkhg=Integer.parseInt(intent.getExtras().getString("checkhg"));
        connect=Integer.parseInt(intent.getExtras().getString("connect"));
        device=intent.getExtras().getString("device") ;

        bluetoothSocket = null;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        String id=ID;
        if(connect==1){
            Close.setVisibility(View.GONE);
            Open.setVisibility(View.VISIBLE);
        }
        else {
            Open.setVisibility(View.GONE);
            Close.setVisibility(View.VISIBLE);
        }

        bluetoothOn();

        Close.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                if(conn ==0) {
                    bluetoothOn();
                }
                if(conn ==1) {
                    Close.setVisibility(View.GONE);
                    Open.setVisibility(View.VISIBLE);
                    sendData(open);
                    String oc = "open";
                    connect =1;
                    service.userOpen(new OpenData(id,oc)).enqueue(new Callback<OpenResponse>() {
                        @Override
                        public void onResponse(Call<OpenResponse> call, Response<OpenResponse> response) {
                            OpenResponse result = response.body();
                            if(result.getCode()==200){
                                Toast.makeText(getApplicationContext(),result.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<OpenResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "오픈기록 에러 발생", Toast.LENGTH_SHORT).show();
                            Log.e("오픈기록 에러 발생", t.getMessage());
                        }
                    });
                }
            }

        });

        Open.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                if(conn ==0) {
                    bluetoothOn();
                }
                if(conn ==1) {
                    Open.setVisibility(View.GONE);
                    Close.setVisibility(View.VISIBLE);
                    sendData(open);
                    String oc = "close";
                    connect =0;
                    service.userOpen(new OpenData(id,oc)).enqueue(new Callback<OpenResponse>() {
                        @Override
                        public void onResponse(Call<OpenResponse> call, Response<OpenResponse> response) {
                            OpenResponse result = response.body();
                            if(result.getCode()==200){
                                Toast.makeText(getApplicationContext(),result.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<OpenResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "오픈기록 에러 발생", Toast.LENGTH_SHORT).show();
                            Log.e("오픈기록 에러 발생", t.getMessage());
                        }
                    });
                }
            }

        });

        service = RetrofitClient.getClient().create(ServiceApi.class);
        Noticelist = (ListView)findViewById(R.id.Noticelist);
        String admin="1";

        service.userNoticeList(new NoticeData(admin)).enqueue(new Callback<NoticeResponse>() {
            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {
                NoticeResponse result = response.body();

                NoticeAdapter adapter = new NoticeAdapter();
                Noticelist.setAdapter(adapter);
                for(NoticeResponse.Row item:result.getRows()){
                    String date=item.getDate().split("T")[0];
                    adapter.addItem(item.getTitle(),date,item.getName(),item.getNotice());

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "공지사항 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("공지사항 에러 발생", t.getMessage());
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
                if(conn==1){
                    try {
                        bluetoothSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
                if(conn==1){
                    try {
                        bluetoothSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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

    private void bluetoothOn() {

        if(bluetoothAdapter == null) { // 디바이스가 블루투스를 지원하지 않을 때
            Toast.makeText(getApplicationContext(),"디바이스가 블루투스를 지원하지 않습니다",Toast.LENGTH_SHORT).show();
        }

        else { // 디바이스가 블루투스를 지원 할 때

            if(bluetoothAdapter.isEnabled()) { // 블루투스가 활성화 상태 (기기에 블루투스가 켜져있음)
                selectBluetoothDevice(); // 블루투스 디바이스 선택 함수 호출
            }

            else { // 블루투스가 비 활성화 상태 (기기에 블루투스가 꺼져있음)

                // 블루투스를 활성화 하기 위한 다이얼로그 출력
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                // 선택한 값이 onActivityResult 함수에서 콜백된다.
                startActivityForResult(intent, REQUEST_ENABLE_BT);
                selectBluetoothDevice(); // 블루투스 디바이스 선택 함수 호출

            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQUEST_ENABLE_BT :

                if(requestCode == RESULT_OK) { // '사용'을 눌렀을 때
                    selectBluetoothDevice(); // 블루투스 디바이스 선택 함수 호출
                }

                else { // '취소'를 눌렀을 때
                }
                break;
        }
    }

    public void selectBluetoothDevice() {

        // 이미 페어링 되어있는 블루투스 기기를 찾습니다.
        devices = bluetoothAdapter.getBondedDevices();

        // 페어링 된 디바이스의 크기를 저장
        int pariedDeviceCount = devices.size();

        // 페어링 되어있는 장치가 없는 경우
        if(pariedDeviceCount == 0) {
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("페어링 되어있는 블루투스 디바이스 목록");
            List<String> list = new ArrayList<>();
            for (BluetoothDevice bluetoothDevice : devices) {
                list.add(bluetoothDevice.getName());
            }

            list.add("취소");

            final CharSequence[] charSequences = list.toArray(new CharSequence[list.size()]);

            list.toArray(new CharSequence[list.size()]);

            builder.setItems(charSequences, new DialogInterface.OnClickListener() {

                @Override

                public void onClick(DialogInterface dialog, int which) {
                    if(!charSequences[which].toString().equals("취소")){
                        connectDevice(charSequences[which].toString());
                        conn =1;
                    }
                    else {
                        conn =0;
                    }

                }

            });

            builder.setCancelable(false);

            AlertDialog alertDialog = builder.create();

            alertDialog.show();

        }

    }

    public void connectDevice(String deviceName) {

        for(BluetoothDevice tempDevice : devices) {


            if(deviceName.equals(tempDevice.getName())) {

                bluetoothDevice = tempDevice;

                break;

            }

        }

        UUID uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        try {

            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);

            bluetoothSocket.connect();

            outputStream = bluetoothSocket.getOutputStream();

            inputStream = bluetoothSocket.getInputStream();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    void sendData(String recvChar) {
        String a=recvChar;
        recvChar += "\n";
        try{

            outputStream.write(recvChar.getBytes());

        }catch(Exception e) {

            e.printStackTrace();

        }

    }

}
