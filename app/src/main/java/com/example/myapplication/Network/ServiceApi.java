package com.example.myapplication.Network;

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

    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/user/join2")
    Call<JoinResponse2> userJoin2(@Body JoinData2 data);

    @POST("/user/menu")
    Call<NoticelistImport> userNotice(@Body NoticelistData data);
/*
    @POST("/user/menu2")
    Call<MenuImport> userMenu2(@Body MenuImData data);
*/
}
