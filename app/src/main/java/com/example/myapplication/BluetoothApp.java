package com.example.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class BluetoothApp  extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 10; // 블루투스 활성화 상태

    private Set<BluetoothDevice> devices; // 블루투스 디바이스 데이터 셋

    private BluetoothDevice bluetoothDevice; // 블루투스 디바이스

    private BluetoothSocket bluetoothSocket = null; // 블루투스 소켓

    private static OutputStream outputStream = null; // 블루투스에 데이터를 출력하기 위한 출력 스트림

    private InputStream inputStream = null; // 블루투스에 데이터를 입력하기 위한 입력 스트림

    private Thread workerThread = null; // 문자열 수신에 사용되는 쓰레드

    private byte[] readBuffer; // 수신 된 문자열을 저장하기 위한 버퍼

    private int readBufferPosition; // 버퍼 내 문자 저장 위치


    /*
    public void receiveData() {

        final Handler handler = new Handler();

        // 데이터를 수신하기 위한 버퍼를 생성

        readBufferPosition = 0;

        readBuffer = new byte[1024];



        // 데이터를 수신하기 위한 쓰레드 생성

        workerThread = new Thread(new Runnable() {

            @Override

            public void run() {

                while(Thread.currentThread().isInterrupted()) {

                    try {

                        // 데이터를 수신했는지 확인합니다.

                        int byteAvailable = inputStream.available();

                        // 데이터가 수신 된 경우

                        if(byteAvailable > 0) {

                            // 입력 스트림에서 바이트 단위로 읽어 옵니다.

                            byte[] bytes = new byte[byteAvailable];

                            inputStream.read(bytes);

                            // 입력 스트림 바이트를 한 바이트씩 읽어 옵니다.

                            for(int i = 0; i < byteAvailable; i++) {

                                byte tempByte = bytes[i];

                                // 개행문자를 기준으로 받음(한줄)

                                if(tempByte == '\n') {

                                    // readBuffer 배열을 encodedBytes로 복사

                                    byte[] encodedBytes = new byte[readBufferPosition];

                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);

                                    // 인코딩 된 바이트 배열을 문자열로 변환

                                    final String text = new String(encodedBytes, "US-ASCII");

                                    readBufferPosition = 0;

                                    handler.post(new Runnable() {

                                        @Override

                                        public void run() {

                                            // 텍스트 뷰에 출력

                                            textViewReceive.append(text + "\n");

                                        }

                                    });

                                } // 개행 문자가 아닐 경우

                                else {

                                    readBuffer[readBufferPosition++] = tempByte;

                                }

                            }

                        }

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                    try {

                        // 1초마다 받아옴

                        Thread.sleep(1000);

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                }

            }

        });

        workerThread.start();

    }
    */

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
