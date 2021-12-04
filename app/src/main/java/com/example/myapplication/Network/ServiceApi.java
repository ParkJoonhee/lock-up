package com.example.myapplication.Network;

import com.example.myapplication.data.GeustlistData;
import com.example.myapplication.data.GeustlistResponse;
import com.example.myapplication.data.JoinData;
import com.example.myapplication.data.JoinData2;
import com.example.myapplication.data.JoinResponse;
import com.example.myapplication.data.JoinResponse2;
import com.example.myapplication.data.LoginData;
import com.example.myapplication.data.LoginResponse;
import com.example.myapplication.data.NoticeData;
import com.example.myapplication.data.NoticeData2;
import com.example.myapplication.data.NoticeResponse;
import com.example.myapplication.data.NoticeResponse2;
import com.example.myapplication.data.OpenData;
import com.example.myapplication.data.OpenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {

    @POST("/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/setUserData")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/double_check")
    Call<JoinResponse2> userJoin2(@Body JoinData2 data);

    @POST("/noticelist")
    Call<NoticeResponse> userNoticeList(@Body NoticeData data);

    @POST("/noticeadd")
    Call<NoticeResponse2> userNoticeAdd(@Body NoticeData2 data);

    @POST("/open")
    Call<OpenResponse> userOpen(@Body OpenData data);

}
