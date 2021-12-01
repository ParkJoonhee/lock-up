package com.example.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SettingActivity extends AppCompatActivity {

    String ID;
    int checkhg;
    String name;

    private Button main, notice, pw, code, setting, mypage;
    private ToggleButton bluetoothBtn;


    private static final int REQUEST_ENABLE_BT = 10; // 블루투스 활성화 상태

    private BluetoothAdapter bluetoothAdapter; // 블루투스 어댑터

    private Set<BluetoothDevice> devices; // 블루투스 디바이스 데이터 셋

    private BluetoothDevice bluetoothDevice; // 블루투스 디바이스

    private BluetoothSocket bluetoothSocket = null; // 블루투스 소켓

    private static OutputStream outputStream = null; // 블루투스에 데이터를 출력하기 위한 출력 스트림

    private static InputStream inputStream = null; // 블루투스에 데이터를 입력하기 위한 입력 스트림


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        main = (Button) findViewById(R.id.main1);
        notice =(Button) findViewById(R.id.notice);
        pw = (Button) findViewById(R.id.Pw1);
        code = (Button) findViewById(R.id.Code1);
        setting = (Button) findViewById(R.id.Setting1);
        mypage = (Button) findViewById(R.id.mypage);
        bluetoothBtn = (ToggleButton) findViewById(R.id.bluetoothBtn);


        // 블루투스 활성화하기
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); // 블루투스 어댑터를 디폴트 어댑터로 설정

        Intent intent = getIntent();
        ID=intent.getExtras().getString("ID") ;
        name=intent.getExtras().getString("name") ;
        checkhg=Integer.parseInt(intent.getExtras().getString("checkhg"));


        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent = null;
                intent = new Intent(getApplicationContext(), com.example.myapplication.HMenuActivity.class);
                startActivity(intent);
                 */
            }
        });

        bluetoothBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(bluetoothAdapter == null) { // 디바이스가 블루투스를 지원하지 않을 때

                        // 여기에 처리 할 코드를 작성하세요.
                        Toast.makeText(SettingActivity.this, "디바이스가 블루투스를 지원하지 않음", Toast.LENGTH_SHORT).show();

                    }

                    else { // 디바이스가 블루투스를 지원 할 때

                        if(bluetoothAdapter.isEnabled()) { // 블루투스가 활성화 상태 (기기에 블루투스가 켜져있음)
                            Toast.makeText(SettingActivity.this, "기기에 블루투스가 켜져있음", Toast.LENGTH_SHORT).show();

                            selectBluetoothDevice(); // 블루투스 디바이스 선택 함수 호출

                        }

                        else { // 블루투스가 비 활성화 상태 (기기에 블루투스가 꺼져있음)

                            // 블루투스를 활성화 하기 위한 다이얼로그 출력
                            Toast.makeText(SettingActivity.this, "기기에 블루투스가 꺼져있음", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                            // 선택한 값이 onActivityResult 함수에서 콜백된다.

                            startActivityForResult(intent, REQUEST_ENABLE_BT);

                        }
                    }


                } else {

                }
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

    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);



        switch (requestCode) {

            case REQUEST_ENABLE_BT :

                if(requestCode == RESULT_OK) { // '사용'을 눌렀을 때

                    selectBluetoothDevice(); // 블루투스 디바이스 선택 함수 호출

                }

                else { // '취소'를 눌렀을 때

                    // 여기에 처리 할 코드를 작성하세요.

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

            // 페어링을 하기위한 함수 호출

        }

        // 페어링 되어있는 장치가 있는 경우

        else {

            // 디바이스를 선택하기 위한 다이얼로그 생성

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("페어링 되어있는 블루투스 디바이스 목록");

            // 페어링 된 각각의 디바이스의 이름과 주소를 저장

            List<String> list = new ArrayList<>();

            // 모든 디바이스의 이름을 리스트에 추가

            for(BluetoothDevice bluetoothDevice : devices) {

                list.add(bluetoothDevice.getName());

            }

            list.add("취소");



            // List를 CharSequence 배열로 변경

            final CharSequence[] charSequences = list.toArray(new CharSequence[list.size()]);

            list.toArray(new CharSequence[list.size()]);



            // 해당 아이템을 눌렀을 때 호출 되는 이벤트 리스너

            builder.setItems(charSequences, new DialogInterface.OnClickListener() {

                @Override

                public void onClick(DialogInterface dialog, int which) {

                    // 해당 디바이스와 연결하는 함수 호출

                    connectDevice(charSequences[which].toString());

                }

            });



            // 뒤로가기 버튼 누를 때 창이 안닫히도록 설정

            builder.setCancelable(false);

            // 다이얼로그 생성

            AlertDialog alertDialog = builder.create();

            alertDialog.show();

        }

    }

    public void connectDevice(String deviceName) {

        // 페어링 된 디바이스들을 모두 탐색

        for(BluetoothDevice tempDevice : devices) {

            // 사용자가 선택한 이름과 같은 디바이스로 설정하고 반복문 종료

            if(deviceName.equals(tempDevice.getName())) {

                bluetoothDevice = tempDevice;

                break;

            }

        }

        // UUID 생성

        UUID uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        // Rfcomm 채널을 통해 블루투스 디바이스와 통신하는 소켓 생성

        try {

            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);

            bluetoothSocket.connect();

            // 데이터 송,수신 스트림을 얻어옵니다.

            outputStream = bluetoothSocket.getOutputStream();

            inputStream = bluetoothSocket.getInputStream();

            // 데이터 수신 함수 호출

            //receiveData();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    static void sendData(String text) {

        // 문자열에 개행문자("\n")를 추가해줍니다.

        text += "\n";

        try{

            // 데이터 송신

            outputStream.write(text.getBytes());

        }catch(Exception e) {

            e.printStackTrace();

        }

    }

}
