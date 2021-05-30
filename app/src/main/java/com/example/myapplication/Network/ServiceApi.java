package com.example.myapplication.Network;

import com.example.myapplication.data.CodeData;
import com.example.myapplication.data.CodeImport;
import com.example.myapplication.data.CodeImportData;
import com.example.myapplication.data.CodeResponse;
import com.example.myapplication.data.GeustlistData;
import com.example.myapplication.data.GeustlistResponse;
import com.example.myapplication.data.JoinData;
import com.example.myapplication.data.JoinData2;
import com.example.myapplication.data.JoinResponse;
import com.example.myapplication.data.JoinResponse2;
import com.example.myapplication.data.LoginData;
import com.example.myapplication.data.LoginResponse;
import com.example.myapplication.data.NoticelistData;
import com.example.myapplication.data.NoticelistImport;

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

    @POST("/Geustlist")
    Call<GeustlistResponse> userGeust(@Body GeustlistData data);

    @POST("/user/menu")
    Call<NoticelistImport> userNotice(@Body NoticelistData data);

    @POST("/user/code")
    Call<CodeResponse> userCode(@Body CodeData data);

    @POST("/user/code2")
    Call<CodeImport> userCode2(@Body CodeImportData data);

}
